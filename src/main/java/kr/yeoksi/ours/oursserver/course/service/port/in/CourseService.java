package kr.yeoksi.ours.oursserver.course.service.port.in;

import kr.yeoksi.ours.oursserver.course.domain.Course;

import java.util.List;
import java.util.Optional;


public interface CourseService {
    Course create(Course course, String userId);
    Optional<Course> findById(Long id, String userId);
    List<Course> findAllByUserId(String userId);
    Course update(Course course, String userId);
    void delete(Long id, String userId);

}
