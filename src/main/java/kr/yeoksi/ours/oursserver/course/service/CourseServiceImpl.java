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

        // Place 및 User 연동
        course.setPlacesInCourse(
                course.getPlacesInCourse().stream()
                        .peek(placeInCourse -> placeInCourse.setPlace(placeService.findById(placeInCourse.getPlace().getId())))
                        .toList());
        course.setUser(userService.findById(userId));

        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public Optional<Course> findById(Long id, String userId) {
        Optional<Course> course = courseRepository.findById(id);
        course.ifPresent(courseToValidate -> validateOwnership(courseToValidate, userId));
        return course;
    }

    @Override
    @Transactional
    public List<Course> findAllByUserId(String userId) {
        return courseRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Course update(Course course, String userId) {
        Course courseToUpdate = validateIsExistedAndGet(course.getId());
        validateOwnership(courseToUpdate, userId);

        courseToUpdate.update(course);
        return courseRepository.save(courseToUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id, String userId) {
        Course course = validateIsExistedAndGet(id);
        validateOwnership(course, userId);

        courseRepository.deleteById(id);
    }


    /*** private methods ***/
    private Course validateIsExistedAndGet(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new NotExistedCourseException();
        }
        return course.get();
    }

    private void validateIsDuplicated(Long courseId) {
        if (isExisted(courseId)) {
            throw new DuplicatedCourseException();
        }
    }

    private boolean isExisted(Long courseId) {
        return courseId != null && courseRepository.findById(courseId).isPresent();
    }

    private void validateOwnership(Course course, String userId) {
        if (!userId.equals(course.getUser().getId()))
            throw new NotOwnerOfCourseException();
    }

}
