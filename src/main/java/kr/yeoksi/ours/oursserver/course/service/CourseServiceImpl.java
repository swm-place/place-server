package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.exception.DuplicatedCourseException;
import kr.yeoksi.ours.oursserver.course.exception.NotExistedCourseException;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseService;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public Course create(Course course) {
        validateIsDuplicated(course.getId());
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> findAllByUserId(String userId) {
        return courseRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Course update(Course course) {
        validateIsExisted(course.getId());
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validateIsExisted(id);
        courseRepository.deleteById(id);
    }


    /*** private methods ***/
    private void validateIsExisted(Long courseId) {
        if (!isExisted(courseId)) {
            throw new NotExistedCourseException();
        }
    }

    private void validateIsDuplicated(Long courseId) {
        if (isExisted(courseId)) {
            throw new DuplicatedCourseException();
        }
    }

    private boolean isExisted(Long courseId) {
        return courseId != null && courseRepository.findById(courseId).isPresent();
    }

}
