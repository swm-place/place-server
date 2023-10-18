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
        validateIsDuplicated(course.getId());
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
        validateIsExisted(course.getId());
        return courseRepository.save(course);
    }

    @Override
    public void delete(Long id) {
        validateIsDuplicated(id);
        courseRepository.deleteById(id);
    }


    /*** private methods ***/
    private void validateIsExisted(Long courseId) {
        if (courseId == null || courseRepository.findById(courseId).isEmpty()) {
            throw new NotExistedCourseException();
        }
    }

    private void validateIsDuplicated(Long courseId) {
        if (courseId != null && courseRepository.findById(courseId).isPresent()) {
            throw new DuplicatedCourseException();
        }
    }

}
