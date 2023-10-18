package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.exception.DuplicatedCourseException;
import kr.yeoksi.ours.oursserver.course.exception.NotExistedCourseException;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseService;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course create(Course course) {
        validateDuplicated(course);
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> findAllByUserId(Long userId) {
        return courseRepository.findAllByUserId(userId);
    }

    @Override
    public Course update(Course course) {
        checkExisted(course);
        return courseRepository.save(course);
    }

    @Override
    public void delete(Long id) {

    }


    /*** private methods ***/
    private void checkExisted(Course course) {
        if (course.getId() == null || courseRepository.findById(course.getId()).isEmpty()) {
            throw new NotExistedCourseException();
        }
    }

    private void validateDuplicated(Course course) {
        if (course.getId() != null && courseRepository.findById(course.getId()).isPresent()) {
            throw new DuplicatedCourseException();
        }
    }

}
