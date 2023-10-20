package kr.yeoksi.ours.oursserver.course.service;


import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseService;
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


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CourseServiceIntegrationTest {

    @Autowired CourseService courseService;

    @Autowired UserRepository userRepository;
    @Autowired PlaceRepository placeRepository;


    @Test
    public void 코스를_생성할_수_있다() {
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

        // when
        Course savedCourse = courseService.create(course);

        // then
        assertThat(savedCourse.getId()).isNotNull();
        assertThat(savedCourse.getUser().getId()).isEqualTo(user.getId());
        assertThat(savedCourse.getTitle()).isEqualTo(course.getTitle());
        assertThat(savedCourse.getDescription()).isEqualTo(course.getDescription());
        assertThat(savedCourse.getPlacesInCourse().size()).isEqualTo(course.getPlacesInCourse().size());
    }


    @Test
    public void id로_코스를_조회할_수_있다() {
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
        course.setId(courseService.create(course).getId());

        // when
        Optional<Course> foundCourse = courseService.findById(course.getId());

        // then
        assertThat(foundCourse.isPresent()).isTrue();
        assertThat(foundCourse.get().getId()).isEqualTo(course.getId());
        assertThat(foundCourse.get().getUser().getId()).isEqualTo(user.getId());
        assertThat(foundCourse.get().getTitle()).isEqualTo(course.getTitle());
        assertThat(foundCourse.get().getDescription()).isEqualTo(course.getDescription());
        assertThat(foundCourse.get().getPlacesInCourse().size()).isEqualTo(course.getPlacesInCourse().size());

    }

}