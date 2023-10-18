package kr.yeoksi.ours.oursserver.course.service.port.out;

import kr.yeoksi.ours.oursserver.course.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course persist(Course course);
    Optional<Course> findById(Long id);
    List<Course> findAllByUserId(Long userId);
    Course update(Course course);
}
