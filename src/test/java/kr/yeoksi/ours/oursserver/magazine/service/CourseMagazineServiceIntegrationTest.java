package kr.yeoksi.ours.oursserver.magazine.service;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.exception.NotExistedCourseMagazineException;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineService;
import kr.yeoksi.ours.oursserver.others.domain.Place;
import kr.yeoksi.ours.oursserver.others.domain.User;
import kr.yeoksi.ours.oursserver.others.repository.PlaceRepository;
import kr.yeoksi.ours.oursserver.others.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CourseMagazineServiceIntegrationTest {

    @Autowired CourseMagazineService courseMagazineService;

    @Autowired PlaceRepository placeRepository;
    @Autowired UserRepository userRepository;


    @Test
    public void 코스_매거진을_발행할_수_있다() {
        // given
        // configure user
        User user = User.builder()
                .id("been")
                .email("been@yeoksi.com")
                .nickname("been")
                .build();
        userRepository.save(user);

        // configure places
        Place place1 = Place.builder()
                .id("test1")
                .name("test1")
                .category("test1")
                .build();
        Place place2 = Place.builder()
                .id("test2")
                .name("test2")
                .category("test2")
                .build();
        place1.setId(placeRepository.save(place1));
        place2.setId(placeRepository.save(place2));

        // configure placesInCourseMagazine
        List<PlaceInCourseMagazine> placesInCourseMagazine = new ArrayList<>(
                List.of(
                        PlaceInCourseMagazine.builder()
                                .place(place1)
                                .contents("test1")
                                .order(1)
                                .build(),
                        PlaceInCourseMagazine.builder()
                                .place(place2)
                                .contents("test2")
                                .order(2)
                                .build()
                )
        );

        // configure courseMagazine
        CourseMagazine courseMagazine = CourseMagazine.builder()
                .user(user)
                .title("test")
                .contents("test")
                .placesInCourseMagazine(placesInCourseMagazine)
                .build();


        // when
        CourseMagazine published = courseMagazineService.publish(courseMagazine, user.getId());

        // then
        CourseMagazine found = courseMagazineService.getById(published.getId(), user.getId());
        assertThat(found.getTitle()).isEqualTo(courseMagazine.getTitle());
        assertThat(found.getContents()).isEqualTo(courseMagazine.getContents());
        assertThat(found.getPlacesInCourseMagazine().size()).isEqualTo(courseMagazine.getPlacesInCourseMagazine().size());

        assertThat(found.getPlacesInCourseMagazine().get(0).getPlace().getId()).isEqualTo(place1.getId());
        assertThat(found.getPlacesInCourseMagazine().get(1).getPlace().getId()).isEqualTo(place2.getId());

        assertThat(found.getPlacesInCourseMagazine().get(0).getContents()).isEqualTo(placesInCourseMagazine.get(0).getContents());
        assertThat(found.getPlacesInCourseMagazine().get(1).getContents()).isEqualTo(placesInCourseMagazine.get(1).getContents());

    }

    @Test
    public void 코스_매거진을_수정할_수_있다() {
        // given
        // configure user
        User user = User.builder()
                .id("been")
                .email("been@yeoksi.com")
                .nickname("been")
                .build();
        userRepository.save(user);

        // configure places
        Place place1 = Place.builder()
                .id("test1")
                .name("test1")
                .category("test1")
                .build();
        Place place2 = Place.builder()
                .id("test2")
                .name("test2")
                .category("test2")
                .build();
        place1.setId(placeRepository.save(place1));
        place2.setId(placeRepository.save(place2));

        // configure placesInCourseMagazine
        List<PlaceInCourseMagazine> placesInCourseMagazine = new ArrayList<>(
                List.of(
                        PlaceInCourseMagazine.builder()
                                .place(place1)
                                .contents("test1")
                                .order(1)
                                .build()
                )
        );

        // configure courseMagazine
        CourseMagazine courseMagazine = CourseMagazine.builder()
                .user(user)
                .title("test")
                .contents("test")
                .placesInCourseMagazine(placesInCourseMagazine)
                .build();
        CourseMagazine published = courseMagazineService.publish(courseMagazine, user.getId());

        // when
        courseMagazine.setId(published.getId());
        courseMagazine.setTitle("updated title");
        courseMagazine.setContents("updated contents");

        courseMagazine.setPlacesInCourseMagazine(new ArrayList<>(
                List.of(
                        PlaceInCourseMagazine.builder()
                                .id(published.getPlacesInCourseMagazine().get(0).getId())
                                .place(place1)
                                .contents("test1 updated")
                                .order(2)
                                .build(),
                        PlaceInCourseMagazine.builder()
                                .place(place2)
                                .contents("test2")
                                .order(1)
                                .build()
                ))
        );

        courseMagazineService.update(courseMagazine, user.getId());

        // then
        CourseMagazine found = courseMagazineService.getById(courseMagazine.getId(), user.getId());

        assertThat(found.getTitle()).isEqualTo(courseMagazine.getTitle());
        assertThat(found.getContents()).isEqualTo(courseMagazine.getContents());
        assertThat(found.getPlacesInCourseMagazine().size()).isEqualTo(courseMagazine.getPlacesInCourseMagazine().size());

        assertThat(found.getPlacesInCourseMagazine().get(0).getPlace().getId()).isEqualTo(place1.getId());
        assertThat(found.getPlacesInCourseMagazine().get(1).getPlace().getId()).isEqualTo(place2.getId());

        assertThat(found.getPlacesInCourseMagazine().get(0).getContents()).isEqualTo(courseMagazine.getPlacesInCourseMagazine().get(0).getContents());
        assertThat(found.getPlacesInCourseMagazine().get(1).getContents()).isEqualTo(courseMagazine.getPlacesInCourseMagazine().get(1).getContents());

    }

    @Test(expected = NotExistedCourseMagazineException.class)
    public void 코스_매거진을_삭제할_수_있다() {
        // given
        // configure user
        User user = User.builder()
                .id("been")
                .email("been@yeoksi.com")
                .nickname("been")
                .build();
        userRepository.save(user);

        // configure places
        Place place1 = Place.builder()
                .id("test1")
                .name("test1")
                .category("test1")
                .build();
        place1.setId(placeRepository.save(place1));

        // configure placesInCourseMagazine
        List<PlaceInCourseMagazine> placesInCourseMagazine = new ArrayList<>(
                List.of(
                        PlaceInCourseMagazine.builder()
                                .place(place1)
                                .contents("test1")
                                .order(1)
                                .build()
                )
        );

        // configure courseMagazine
        CourseMagazine courseMagazine = CourseMagazine.builder()
                .user(user)
                .title("test")
                .contents("test")
                .placesInCourseMagazine(placesInCourseMagazine)
                .build();
        CourseMagazine published = courseMagazineService.publish(courseMagazine, user.getId());

        // when
        courseMagazineService.delete(published.getId(), user.getId());

        // then
        // should throw NotExistedCourseMagazineException
        // TODO: Junit5 assertThrows 사용
        CourseMagazine found = courseMagazineService.getById(published.getId(), user.getId());
    }

    @Test
    public void id로_코스_매거진을_조회할_수_있다() {
        // given
        // configure user
        User user = User.builder()
                .id("been")
                .email("been@yeoksi.com")
                .nickname("been")
                .build();
        userRepository.save(user);

        // configure places
        Place place1 = Place.builder()
                .id("test1")
                .name("test1")
                .category("test1")
                .build();
        place1.setId(placeRepository.save(place1));

        // configure placesInCourseMagazine
        List<PlaceInCourseMagazine> placesInCourseMagazine = new ArrayList<>(
                List.of(
                        PlaceInCourseMagazine.builder()
                                .place(place1)
                                .contents("test1")
                                .order(1)
                                .build()
                )
        );

        // configure courseMagazine
        CourseMagazine courseMagazine = CourseMagazine.builder()
                .user(user)
                .title("test")
                .contents("test")
                .placesInCourseMagazine(placesInCourseMagazine)
                .build();
        CourseMagazine published = courseMagazineService.publish(courseMagazine, user.getId());

        // when
        CourseMagazine found = courseMagazineService.getById(published.getId(), user.getId());

        // then
        assertThat(found.getTitle()).isEqualTo(courseMagazine.getTitle());
        assertThat(found.getContents()).isEqualTo(courseMagazine.getContents());
        assertThat(found.getPlacesInCourseMagazine().size()).isEqualTo(courseMagazine.getPlacesInCourseMagazine().size());

        assertThat(found.getPlacesInCourseMagazine().get(0).getPlace().getId()).isEqualTo(place1.getId());
        assertThat(found.getPlacesInCourseMagazine().get(0).getContents()).isEqualTo(courseMagazine.getPlacesInCourseMagazine().get(0).getContents());

    }

    @Test
    public void 최신_코스_매거진을_조회할_수_있다() {
        // given
        // configure user
        User user = User.builder()
                .id("been")
                .email("been@yeoksi.com")
                .nickname("been")
                .build();
        userRepository.save(user);

        // configure places
        Place place1 = Place.builder()
                .id("test1")
                .name("test1")
                .category("test1")
                .build();
        place1.setId(placeRepository.save(place1));

        // configure placesInCourseMagazine
        List<PlaceInCourseMagazine> placesInCourseMagazine = new ArrayList<>(
                List.of(
                        PlaceInCourseMagazine.builder()
                                .place(place1)
                                .contents("test1")
                                .order(1)
                                .build()
                )
        );

        // configure courseMagazine
        CourseMagazine courseMagazine1 = CourseMagazine.builder()
                .user(user)
                .title("test1")
                .contents("test1")
                .placesInCourseMagazine(placesInCourseMagazine)
                .build();

        CourseMagazine courseMagazine2 = CourseMagazine.builder()
                .user(user)
                .title("test2")
                .contents("test2")
                .placesInCourseMagazine(placesInCourseMagazine)
                .build();

        CourseMagazine courseMagazine3 = CourseMagazine.builder()
                .user(user)
                .title("test3")
                .contents("test3")
                .placesInCourseMagazine(placesInCourseMagazine)
                .build();

        courseMagazine1 = courseMagazineService.publish(courseMagazine1, user.getId());
        courseMagazine2 = courseMagazineService.publish(courseMagazine2, user.getId());
        courseMagazine3 = courseMagazineService.publish(courseMagazine3, user.getId());

        // when
        List<CourseMagazine> found = courseMagazineService.findLatestCourseMagazines(2, 0);

        // then
        assertThat(found.size()).isEqualTo(2);

        assertThat(found.get(0).getTitle()).isEqualTo(courseMagazine3.getTitle());
        assertThat(found.get(0).getContents()).isEqualTo(courseMagazine3.getContents());

        assertThat(found.get(1).getTitle()).isEqualTo(courseMagazine2.getTitle());
        assertThat(found.get(1).getContents()).isEqualTo(courseMagazine2.getContents());

    }
}
