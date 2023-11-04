package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.CourseInBookmark;
import kr.yeoksi.ours.oursserver.course.exception.DuplicatedCourseException;
import kr.yeoksi.ours.oursserver.course.exception.NotExistedCourseException;
import kr.yeoksi.ours.oursserver.course.exception.NotOwnerOfCourseException;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseInBookmarkService;
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
    private final CourseInBookmarkService courseInBookmarkService;

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

        // ID로 하나씩 조회할 때에만 `bookmarks` 제공
        course.ifPresent(c -> c.setBookmarks(
                courseInBookmarkService.findByCourseId(c.getId(), userId).stream()
                        .map(CourseInBookmark::getCourseBookmark)
                        .toList()));

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

        // TODO: PlaceInCourse의 소유권 validation

        courseToUpdate.update(course);

        // TODO: placesInCourse의 일부 필드만 반환되는 문제 해결
        return courseRepository.save(courseToUpdate);
    }

    // TODO: replace() 추가 (Course의 placesInCourse 전부 교체 지원)

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
