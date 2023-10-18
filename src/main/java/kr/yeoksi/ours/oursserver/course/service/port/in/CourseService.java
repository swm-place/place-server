package kr.yeoksi.ours.oursserver.course.service.port.in;

import kr.yeoksi.ours.oursserver.course.domain.Course;

import java.util.List;
import java.util.Optional;


public interface CourseService {
    Course create(Course course);
    Optional<Course> findById(Long id);
    List<Course> findAllByUserId(String userId);
    Course update(Course course);
    void delete(Long id);

}
