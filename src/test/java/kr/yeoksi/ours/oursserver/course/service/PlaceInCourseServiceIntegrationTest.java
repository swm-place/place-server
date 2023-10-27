package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseService;
import kr.yeoksi.ours.oursserver.course.service.port.in.PlaceInCourseService;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseRepository;
import kr.yeoksi.ours.oursserver.others.domain.Place;
import kr.yeoksi.ours.oursserver.others.domain.User;
import kr.yeoksi.ours.oursserver.others.repository.PlaceRepository;
import kr.yeoksi.ours.oursserver.others.repository.UserRepository;
import kr.yeoksi.ours.oursserver.others.service.UserService;
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

        // then
        assertThat(saved.getId()).isEqualTo(placeInCourse.getId());
        assertThat(saved.getCourseId()).isEqualTo(placeInCourse.getCourseId());
        assertThat(saved.getDay()).isEqualTo(placeInCourse.getDay());
        assertThat(saved.getOrder()).isEqualTo(placeInCourse.getOrder());
        assertThat(saved.getStartAt()).isEqualTo(placeInCourse.getStartAt());
        assertThat(saved.getTimeRequired()).isEqualTo(placeInCourse.getTimeRequired());
        assertThat(saved.getTransportationTime()).isEqualTo(placeInCourse.getTransportationTime());

        assertThat(saved.getPlace().getId()).isEqualTo(place.getId());
        assertThat(saved.getPlace().getName()).isEqualTo(place.getName());
        assertThat(saved.getPlace().getCategory()).isEqualTo(place.getCategory());

    }

    @Test
    public void 코스_내_장소를_수정할_수_있다() {
        // given

        // when

        // then
    }

    @Test
    public void 코스_내_장소를_삭제할_수_있다() {
        // given

        // when

        // then
    }

    @Test
    public void 코스의_id로_코스_내_모든_장소들을_조회할_수_있다() {
        // given

        // when

        // then
    }

    @Test
    public void id로_코스_내_장소를_조회할_수_있다() {
        // given

        // when

        // then
    }

}
