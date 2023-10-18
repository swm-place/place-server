package kr.yeoksi.ours.oursserver.course.service.port.out;

import kr.yeoksi.ours.oursserver.course.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course save(Course course);
    Optional<Course> findById(Long id);
    List<Course> findAllByUserId(String userId);
    void deleteById(Long id);
}
