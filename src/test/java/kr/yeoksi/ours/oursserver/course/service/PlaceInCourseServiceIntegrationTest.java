package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.course.exception.NotExistedPlaceInCourseException;
import kr.yeoksi.ours.oursserver.course.service.port.in.PlaceInCourseService;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseRepository;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlaceInCourseServiceIntegrationTest {

    @Autowired PlaceInCourseService placeInCourseService;

    @Autowired UserRepository userRepository;
    @Autowired PlaceRepository placeRepository;
    @Autowired CourseRepository courseRepository;


    @Test
    public void 코스_내_장소를_추가할_수_있다() {
        // given
        // configure user
        User user = User.builder()
                .id("test")
                .email("test@yeoksi.com")
                .nickname("test")
                .build();
        userRepository.save(user);

        // configure places
        Place place = Place.builder()
                .id("test1")
                .name("test1")
                .category("test1")
                .build();
        place.setId(placeRepository.save(place));

        // configure course
        Course course = Course.builder()
                .user(user)
                .title("test")
                .description("test")
                .build();
        course = courseRepository.save(course);

        // configure placesInCourse
        PlaceInCourse placeInCourse = PlaceInCourse.builder()
                .courseId(course.getId())
                .place(Place.builder().id(place.getId()).build())
                .day(1)
                .order(1)
                .startAt(null)
                .timeRequired(0)
                .transportationTime(0)
                .build();

        // when
        PlaceInCourse saved = placeInCourseService.append(placeInCourse, user.getId());
        Optional<Course> updatedCourse = courseRepository.findById(course.getId());

        // then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCourseId()).isEqualTo(placeInCourse.getCourseId());
        assertThat(saved.getDay()).isEqualTo(placeInCourse.getDay());
        assertThat(saved.getOrder()).isEqualTo(placeInCourse.getOrder());
        assertThat(saved.getStartAt()).isEqualTo(placeInCourse.getStartAt());
        assertThat(saved.getTimeRequired()).isEqualTo(placeInCourse.getTimeRequired());
        assertThat(saved.getTransportationTime()).isEqualTo(placeInCourse.getTransportationTime());

        assertThat(saved.getPlace().getId()).isEqualTo(place.getId());
        assertThat(saved.getPlace().getName()).isEqualTo(place.getName());
        assertThat(saved.getPlace().getCategory()).isEqualTo(place.getCategory());

        assertThat(updatedCourse.isPresent()).isTrue();
        assertThat(updatedCourse.get().getPlacesInCourse().size()).isEqualTo(1);
        assertThat(updatedCourse.get().getPlacesInCourse().get(0).getId()).isEqualTo(saved.getId());

    }

    @Test
    public void 코스_내_장소를_수정할_수_있다() {
        // given

        // configure user
        User user = User.builder()
                .id("test")
                .email("test@yeoksi.com")
                .nickname("test")
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

        // configure course
        Course course = Course.builder()
                .user(user)
                .title("test")
                .description("test")
                .build();
        course = courseRepository.save(course);

        // configure placesInCourse
        PlaceInCourse placeInCourse = PlaceInCourse.builder()
                .courseId(course.getId())
                .place(Place.builder().id(place1.getId()).build())
                .day(1)
                .order(1)
                .startAt(null)
                .timeRequired(0)
                .transportationTime(0)
                .build();
        placeInCourse = placeInCourseService.append(placeInCourse, user.getId());

        // when
        placeInCourse.setPlace(Place.builder().id(place2.getId()).build());
        placeInCourse.setTimeRequired(10);
        PlaceInCourse updated = placeInCourseService.update(placeInCourse, user.getId());
        Optional<Course> updatedCourse = courseRepository.findById(course.getId());

        // then
        assertThat(updated.getId()).isEqualTo(placeInCourse.getId());
        assertThat(updated.getCourseId()).isEqualTo(placeInCourse.getCourseId());
        assertThat(updated.getDay()).isEqualTo(placeInCourse.getDay());
        assertThat(updated.getOrder()).isEqualTo(placeInCourse.getOrder());
        assertThat(updated.getTimeRequired()).isEqualTo(10);

        assertThat(updated.getPlace().getId()).isEqualTo(place2.getId());
        assertThat(updated.getPlace().getName()).isEqualTo(place2.getName());
        assertThat(updated.getPlace().getCategory()).isEqualTo(place2.getCategory());

        assertThat(updatedCourse.isPresent()).isTrue();
        assertThat(updatedCourse.get().getPlacesInCourse().size()).isEqualTo(1);
        assertThat(updatedCourse.get().getPlacesInCourse().get(0).getId()).isEqualTo(updated.getId());
        assertThat(updatedCourse.get().getPlacesInCourse().get(0).getPlace().getId()).isEqualTo(place2.getId());

    }

    @Test
    public void 코스_내_장소를_삭제할_수_있다() {
        // given
        // configure user
        User user = User.builder()
                .id("test")
                .email("test@yeoksi.com")
                .nickname("test")
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

        // configure placesInCourse
        List<PlaceInCourse> placesInCourse = new ArrayList<>();
        placesInCourse.add(PlaceInCourse.builder()
                .place(place1)
                .day(1)
                .order(1)
                .startAt(null)
                .timeRequired(0)
                .transportationTime(0)
                .build());
        placesInCourse.add(PlaceInCourse.builder()
                .place(place2)
                .day(1)
                .order(2)
                .startAt(null)
                .timeRequired(0)
                .transportationTime(0)
                .build());

        // configure course
        Course course = Course.builder()
                .user(user)
                .title("test")
                .description("test")
                .placesInCourse(placesInCourse)
                .build();
        course = courseRepository.save(course);

        // when
        placeInCourseService.delete(course.getPlacesInCourse().get(0).getId(), course.getId(), user.getId());
        Optional<Course> updatedCourse = courseRepository.findById(course.getId());

        // then
        assertThat(updatedCourse.isPresent()).isTrue();
        assertThat(updatedCourse.get().getPlacesInCourse().size()).isEqualTo(1);
        assertThat(updatedCourse.get().getPlacesInCourse().get(0).getPlace().getId()).isEqualTo(place2.getId());

    }

    @Test
    public void 코스의_id로_코스_내_모든_장소들을_조회할_수_있다() {
        // given
        // configure user
        User user = User.builder()
                .id("test")
                .email("test@yeoksi.com")
                .nickname("test")
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

        // configure placesInCourse
        List<PlaceInCourse> placesInCourse = new ArrayList<>();
        placesInCourse.add(PlaceInCourse.builder()
                .place(place1)
                .day(1)
                .order(1)
                .startAt(null)
                .timeRequired(0)
                .transportationTime(0)
                .build());
        placesInCourse.add(PlaceInCourse.builder()
                .place(place2)
                .day(1)
                .order(2)
                .startAt(null)
                .timeRequired(0)
                .transportationTime(0)
                .build());

        // configure course
        Course course = Course.builder()
                .user(user)
                .title("test")
                .description("test")
                .placesInCourse(placesInCourse)
                .build();
        course = courseRepository.save(course);

        // when
        List<PlaceInCourse> placesInCourseFound = placeInCourseService.findByCourseId(course.getId(), user.getId());

        // then
        assertThat(placesInCourseFound.size()).isEqualTo(2);
        assertThat(placesInCourseFound.get(0).getPlace().getId()).isEqualTo(place1.getId());
        assertThat(placesInCourseFound.get(1).getPlace().getId()).isEqualTo(place2.getId());
    }

    @Test
    public void id로_코스_내_장소를_조회할_수_있다() {
        // given
        // configure user
        User user = User.builder()
                .id("test")
                .email("test@yeoksi.com")
                .nickname("test")
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

        // configure placesInCourse
        List<PlaceInCourse> placesInCourse = new ArrayList<>();
        placesInCourse.add(PlaceInCourse.builder()
                .place(place1)
                .day(1)
                .order(1)
                .startAt(null)
                .timeRequired(0)
                .transportationTime(0)
                .build());
        placesInCourse.add(PlaceInCourse.builder()
                .place(place2)
                .day(1)
                .order(2)
                .startAt(null)
                .timeRequired(0)
                .transportationTime(0)
                .build());

        // configure course
        Course course = Course.builder()
                .user(user)
                .title("test")
                .description("test")
                .placesInCourse(placesInCourse)
                .build();
        course = courseRepository.save(course);

        // when
        PlaceInCourse placeInCourseFound = placeInCourseService.getById(course.getPlacesInCourse().get(0).getId(), course.getId(), user.getId());

        // then
        assertThat(placeInCourseFound.getPlace().getId()).isEqualTo(place1.getId());
        assertThat(placeInCourseFound.getPlace().getName()).isEqualTo(place1.getName());
        assertThat(placeInCourseFound.getPlace().getCategory()).isEqualTo(place1.getCategory());

    }

}
