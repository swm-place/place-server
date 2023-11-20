package kr.yeoksi.ours.oursserver.course.service.port.out;

import kr.yeoksi.ours.oursserver.course.domain.CourseInBookmark;

import java.util.List;
import java.util.Optional;

public interface CourseInBookmarkRepository {

    CourseInBookmark save(CourseInBookmark courseInBookmark);

    Optional<CourseInBookmark> findById(Long courseInBookmarkId);

    Optional<CourseInBookmark> findByCourseBookmarkIdAndCourseId(Long courseBookmarkId, Long courseId);

    List<CourseInBookmark> findByCourseBookmarkId(Long courseBookmarkId);

    List<CourseInBookmark> findByCourseBookmarkIdWithPage(Long courseBookmarkId, int page, int size);

    boolean existsByCourseBookmarkIdAndCourseId(Long courseBookmarkId, Long courseId);

    void deleteById(Long courseInBookmarkId);

    List<CourseInBookmark> findByCourseId(Long courseId);

}
