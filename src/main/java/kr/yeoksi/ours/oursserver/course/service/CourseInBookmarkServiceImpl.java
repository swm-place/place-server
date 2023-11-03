package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import kr.yeoksi.ours.oursserver.course.domain.CourseInBookmark;
import kr.yeoksi.ours.oursserver.course.exception.DuplicatedBookmarkException;
import kr.yeoksi.ours.oursserver.course.exception.NotExistedCourseInBookmarkException;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseBookmarkService;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseInBookmarkService;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseInBookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseInBookmarkServiceImpl implements CourseInBookmarkService {

    private final CourseInBookmarkRepository courseInBookmarkRepository;

    private final CourseBookmarkService courseBookmarkService;


    @Override
    public void addCourseToBookmark(Long courseBookmarkId, Long courseId, String userId) {
        // validate permission
        CourseBookmark courseBookmark = courseBookmarkService.getCourseBookmark(courseBookmarkId, userId);

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
        // validate permission
        CourseBookmark courseBookmark = courseBookmarkService.getCourseBookmark(courseBookmarkId, userId);

        CourseInBookmark courseInBookmark = courseInBookmarkRepository
                .findByCourseBookmarkIdAndCourseId(courseBookmark.getId(), courseId)
                .orElseThrow(NotExistedCourseInBookmarkException::new);

        courseInBookmarkRepository.deleteById(courseInBookmark.getId());
    }

    @Override
    public List<CourseInBookmark> findByCourseId(Long courseId, String userId) {
        List<CourseInBookmark> courseInBookmarks = courseInBookmarkRepository.findByCourseId(courseId);

        courseInBookmarks = courseInBookmarks.stream()
                .filter(courseInBookmark -> userId.equals(courseInBookmark.getCourseBookmark().getUser().getId()))
                .toList();

        return courseInBookmarks;
    }
}
