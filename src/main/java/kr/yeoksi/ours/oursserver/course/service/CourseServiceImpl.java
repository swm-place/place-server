package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.exception.DuplicatedCourseException;
import kr.yeoksi.ours.oursserver.course.exception.NotExistedCourseException;
import kr.yeoksi.ours.oursserver.course.exception.NotOwnerOfCourseException;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseService;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseRepository;
import kr.yeoksi.ours.oursserver.others.service.PlaceService;
import kr.yeoksi.ours.oursserver.others.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final UserService userService;
    private final PlaceService placeService;

    @Override
    @Transactional
    public Course create(Course course, String userId) {
        validateIsDuplicated(course.getId());

        course.setPlacesInCourse(
                course.getPlacesInCourse().stream()
                        .peek(placeInCourse -> placeInCourse.setPlace(placeService.findById(placeInCourse.getPlace().getId())))
                        .toList()
        );

        course.setUser(userService.findById(userId));
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> findById(Long id, String userId) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            if (course.get().getUser().getId().equals(userId)) {
                return course;
            }
            else {
                throw new NotOwnerOfCourseException();
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Course> findAllByUserId(String userId) {
        return courseRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Course update(Course course, String userId) {
        validateIsExisted(course.getId());

        if (!course.getUser().getId().equals(userId)) {
            throw new NotOwnerOfCourseException();
        }

        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void delete(Long id, String userId) {
        validateIsExisted(id);
        Optional<Course> course = courseRepository.findById(id);

        if (!course.get().getUser().getId().equals(userId)) {
            throw new NotOwnerOfCourseException();
        }

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
