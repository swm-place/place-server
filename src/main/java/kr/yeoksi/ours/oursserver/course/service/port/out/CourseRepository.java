package kr.yeoksi.ours.oursserver.course.service.port.out;

import kr.yeoksi.ours.oursserver.course.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    public Course save(Course course);
    public Optional<Course> findById(Long id);

    public List<Course> findAllByUserId(Long userId);
}
