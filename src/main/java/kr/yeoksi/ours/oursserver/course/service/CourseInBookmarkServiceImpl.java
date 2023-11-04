package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import kr.yeoksi.ours.oursserver.course.domain.CourseInBookmark;
import kr.yeoksi.ours.oursserver.course.exception.DuplicatedBookmarkException;
import kr.yeoksi.ours.oursserver.course.exception.NotExistedCourseException;
import kr.yeoksi.ours.oursserver.course.exception.NotExistedCourseInBookmarkException;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseBookmarkService;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseInBookmarkService;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseService;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseInBookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseInBookmarkServiceImpl implements CourseInBookmarkService {

    private final CourseInBookmarkRepository courseInBookmarkRepository;

    private final CourseBookmarkService courseBookmarkService;
    private final CourseService courseService;


    @Override
    public void addCourseToBookmark(Long courseBookmarkId, Long courseId, String userId) {
        CourseBookmark courseBookmark = courseBookmarkService.getCourseBookmark(courseBookmarkId, userId);
        validateExistenceAndPermissionOfCourse(courseId, userId);

        if (courseInBookmarkRepository.existsByCourseBookmarkIdAndCourseId(courseBookmark.getId(), courseId)) {
            throw new DuplicatedBookmarkException();
        }

        CourseInBookmark courseInBookmark = CourseInBookmark.builder()
                .courseBookmark(courseBookmark)
                .course(Course.builder().id(courseId).build())
                .build();

        courseInBookmarkRepository.save(courseInBookmark);
    }

    @Override
    public void deleteCourseInBookmark(Long courseBookmarkId, Long courseId, String userId) {
        CourseBookmark courseBookmark = courseBookmarkService.getCourseBookmark(courseBookmarkId, userId);

        CourseInBookmark courseInBookmark = courseInBookmarkRepository
                .findByCourseBookmarkIdAndCourseId(courseBookmark.getId(), courseId)
                .orElseThrow(NotExistedCourseInBookmarkException::new);

        courseInBookmarkRepository.deleteById(courseInBookmark.getId());
    }

    private void validateExistenceAndPermissionOfCourse(Long courseId, String userId) {
        if (courseService.findById(courseId, userId).isEmpty()) {
            throw new NotExistedCourseException();
        }
    }

}
