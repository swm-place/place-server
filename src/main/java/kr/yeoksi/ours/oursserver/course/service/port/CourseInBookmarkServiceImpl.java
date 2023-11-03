package kr.yeoksi.ours.oursserver.course.service.port;

import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import kr.yeoksi.ours.oursserver.course.domain.CourseInBookmark;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseBookmarkService;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseInBookmarkService;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseInBookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CourseInBookmarkServiceImpl implements CourseInBookmarkService {

    private final CourseInBookmarkRepository courseInBookmarkRepository;

    private final CourseBookmarkService courseBookmarkService;


    @Override
    public void addCourseToBookmark(Long courseBookmarkId, Long courseId, String userId) {
        // validate permission
        CourseBookmark courseBookmark = courseBookmarkService.getCourseBookmark(courseBookmarkId, userId);

        CourseInBookmark courseInBookmark = CourseInBookmark.builder()
                .courseBookmark(courseBookmark)
                .course(Course.builder().id(courseId).build())
                .build();

        courseInBookmarkRepository.save(courseInBookmark);
    }

    @Override
    public void deleteCourseInBookmark(Long courseBookmarkId, Long courseId, String userId) {
        // validate permission
        CourseBookmark courseBookmark = courseBookmarkService.getCourseBookmark(courseBookmarkId, userId);

        CourseInBookmark courseInBookmark = courseInBookmarkRepository
                .findByCourseBookmarkIdAndCourseId(courseBookmark.getId(), courseId)
                .orElseThrow();

        courseInBookmarkRepository.deleteById(courseInBookmark.getId());
    }
}
