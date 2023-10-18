package kr.yeoksi.ours.oursserver.course.service.port.in;

import kr.yeoksi.ours.oursserver.course.domain.Course;

import java.util.List;
import java.util.Optional;


public interface CourseService {
    public Course create(Course course);
    public Optional<Course> findById(Long id);
    public List<Course> findAllByUserId(Long userId);
    public Course update(Course course);
    public void delete(Long id);

}
