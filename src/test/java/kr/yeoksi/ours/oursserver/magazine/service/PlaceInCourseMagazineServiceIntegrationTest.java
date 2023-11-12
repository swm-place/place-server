package kr.yeoksi.ours.oursserver.magazine.service;


import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineService;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.PlaceInCourseMagazineService;
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

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlaceInCourseMagazineServiceIntegrationTest {

    @Autowired PlaceInCourseMagazineService placeInCourseMagazineService;

    @Autowired CourseMagazineService courseMagazineService;
    @Autowired PlaceRepository placeRepository;
    @Autowired UserRepository userRepository;


    @Test
    public void 코스_매거진_내_장소를_추가할_수_있다() {
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
        PlaceInCourseMagazine placeInCourseMagazine1 = PlaceInCourseMagazine.builder()
                .place(place1)
                .contents("test1")
                .order(1)
                .build();
        PlaceInCourseMagazine placeInCourseMagazine2 = PlaceInCourseMagazine.builder()
                .place(place2)
                .contents("test2")
                .order(2)
                .build();
        List<PlaceInCourseMagazine> placesInCourseMagazine = new ArrayList<>(
                List.of(placeInCourseMagazine1)
        );

        // configure courseMagazine
        CourseMagazine courseMagazine = CourseMagazine.builder()
                .user(user)
                .title("test")
                .contents("test")
                .placesInCourseMagazine(placesInCourseMagazine)
                .build();
        courseMagazine = courseMagazineService.publish(courseMagazine, user.getId());


        // when
        placeInCourseMagazineService.append(placeInCourseMagazine2, courseMagazine.getId(), user.getId());


        // then
        CourseMagazine found = courseMagazineService.getById(courseMagazine.getId(), user.getId());
        assertThat(found.getPlacesInCourseMagazine().size()).isEqualTo(2);

        assertThat(found.getPlacesInCourseMagazine().get(0).getPlace().getId()).isEqualTo(place1.getId());
        assertThat(found.getPlacesInCourseMagazine().get(0).getContents()).isEqualTo("test1");
        assertThat(found.getPlacesInCourseMagazine().get(0).getOrder()).isEqualTo(1);

        assertThat(found.getPlacesInCourseMagazine().get(1).getPlace().getId()).isEqualTo(place2.getId());
        assertThat(found.getPlacesInCourseMagazine().get(1).getContents()).isEqualTo("test2");
        assertThat(found.getPlacesInCourseMagazine().get(1).getOrder()).isEqualTo(2);

    }

    @Test
    public void 코스_매거진_내_장소를_수정할_수_있다() {
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
        PlaceInCourseMagazine placeInCourseMagazine = PlaceInCourseMagazine.builder()
                .place(place1)
                .contents("test1")
                .order(1)
                .build();
        List<PlaceInCourseMagazine> placesInCourseMagazine = new ArrayList<>(
                List.of(placeInCourseMagazine)
        );

        // configure courseMagazine
        CourseMagazine courseMagazine = CourseMagazine.builder()
                .user(user)
                .title("test")
                .contents("test")
                .placesInCourseMagazine(placesInCourseMagazine)
                .build();
        courseMagazine = courseMagazineService.publish(courseMagazine, user.getId());


        // when
        placeInCourseMagazine.setId(courseMagazine.getPlacesInCourseMagazine().get(0).getId());
        placeInCourseMagazine.setPlace(place2);
        placeInCourseMagazine.setContents("test2");

        placeInCourseMagazineService.update(placeInCourseMagazine, courseMagazine.getId(), user.getId());


        // then
        CourseMagazine found = courseMagazineService.getById(courseMagazine.getId(), user.getId());

        assertThat(found.getPlacesInCourseMagazine().get(0).getPlace().getId()).isEqualTo(place2.getId());
        assertThat(found.getPlacesInCourseMagazine().get(0).getContents()).isEqualTo("test2");
        assertThat(found.getPlacesInCourseMagazine().get(0).getOrder()).isEqualTo(1);

    }

    @Test
    public void 코스_매거진_내_장소를_삭제할_수_있다() {
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
        PlaceInCourseMagazine placeInCourseMagazine1 = PlaceInCourseMagazine.builder()
                .place(place1)
                .contents("test1")
                .order(1)
                .build();
        PlaceInCourseMagazine placeInCourseMagazine2 = PlaceInCourseMagazine.builder()
                .place(place2)
                .contents("test2")
                .order(2)
                .build();
        List<PlaceInCourseMagazine> placesInCourseMagazine = new ArrayList<>(
                List.of(placeInCourseMagazine1, placeInCourseMagazine2)
        );

        // configure courseMagazine
        CourseMagazine courseMagazine = CourseMagazine.builder()
                .user(user)
                .title("test")
                .contents("test")
                .placesInCourseMagazine(placesInCourseMagazine)
                .build();
        courseMagazine = courseMagazineService.publish(courseMagazine, user.getId());

        // set id of placeInCourseMagazine
        placeInCourseMagazine1.setId(courseMagazine.getPlacesInCourseMagazine().get(0).getId());
        placeInCourseMagazine2.setId(courseMagazine.getPlacesInCourseMagazine().get(1).getId());


        // when
        placeInCourseMagazineService.delete(placeInCourseMagazine1.getId(), courseMagazine.getId(), user.getId());


        // then
        CourseMagazine found = courseMagazineService.getById(courseMagazine.getId(), user.getId());

        assertThat(found.getPlacesInCourseMagazine().size()).isEqualTo(1);
        assertThat(found.getPlacesInCourseMagazine().get(0).getPlace().getId()).isEqualTo(place2.getId());
        assertThat(found.getPlacesInCourseMagazine().get(0).getContents()).isEqualTo("test2");
        assertThat(found.getPlacesInCourseMagazine().get(0).getOrder()).isEqualTo(2);

    }

    @Test
    public void id로_코스_매거진_내_장소를_조회할_수_있다() {
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
        PlaceInCourseMagazine placeInCourseMagazine1 = PlaceInCourseMagazine.builder()
                .place(place1)
                .contents("test1")
                .order(1)
                .build();
        PlaceInCourseMagazine placeInCourseMagazine2 = PlaceInCourseMagazine.builder()
                .place(place2)
                .contents("test2")
                .order(2)
                .build();
        List<PlaceInCourseMagazine> placesInCourseMagazine = new ArrayList<>(
                List.of(placeInCourseMagazine1, placeInCourseMagazine2)
        );

        // configure courseMagazine
        CourseMagazine courseMagazine = CourseMagazine.builder()
                .user(user)
                .title("test")
                .contents("test")
                .placesInCourseMagazine(placesInCourseMagazine)
                .build();
        courseMagazine = courseMagazineService.publish(courseMagazine, user.getId());

        // set id of placeInCourseMagazine
        placeInCourseMagazine1.setId(courseMagazine.getPlacesInCourseMagazine().get(0).getId());
        placeInCourseMagazine2.setId(courseMagazine.getPlacesInCourseMagazine().get(1).getId());


        // when
        PlaceInCourseMagazine found = placeInCourseMagazineService.getById(placeInCourseMagazine1.getId());


        // then
        assertThat(found.getPlace().getId()).isEqualTo(place1.getId());
        assertThat(found.getContents()).isEqualTo("test1");
        assertThat(found.getOrder()).isEqualTo(1);

    }

    @Test
    public void 코스_매거진_id로_코스_매거진_내_장소들을_조회할_수_있다() {
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
        PlaceInCourseMagazine placeInCourseMagazine1 = PlaceInCourseMagazine.builder()
                .place(place1)
                .contents("test1")
                .order(1)
                .build();
        PlaceInCourseMagazine placeInCourseMagazine2 = PlaceInCourseMagazine.builder()
                .place(place2)
                .contents("test2")
                .order(2)
                .build();
        List<PlaceInCourseMagazine> placesInCourseMagazine = new ArrayList<>(
                List.of(placeInCourseMagazine1, placeInCourseMagazine2)
        );

        // configure courseMagazine
        CourseMagazine courseMagazine = CourseMagazine.builder()
                .user(user)
                .title("test")
                .contents("test")
                .placesInCourseMagazine(placesInCourseMagazine)
                .build();
        courseMagazine = courseMagazineService.publish(courseMagazine, user.getId());


        // when
        List<PlaceInCourseMagazine> found = placeInCourseMagazineService.findByMagazineId(courseMagazine.getId());


        // then
        assertThat(found.size()).isEqualTo(2);

        assertThat(found.get(0).getPlace().getId()).isEqualTo(place1.getId());
        assertThat(found.get(0).getContents()).isEqualTo("test1");
        assertThat(found.get(0).getOrder()).isEqualTo(1);

        assertThat(found.get(1).getPlace().getId()).isEqualTo(place2.getId());
        assertThat(found.get(1).getContents()).isEqualTo("test2");
        assertThat(found.get(1).getOrder()).isEqualTo(2);
    }
}
