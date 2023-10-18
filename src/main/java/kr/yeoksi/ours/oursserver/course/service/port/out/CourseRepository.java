package kr.yeoksi.ours.oursserver.course.service.port.out;

import kr.yeoksi.ours.oursserver.course.domain.Course;

public interface CourseRepository {
    public Course save(Course course);
}
