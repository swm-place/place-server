package kr.yeoksi.ours.oursserver.course.service.port.out;

import kr.yeoksi.ours.oursserver.course.domain.CourseInBookmark;

public interface CourseInBookmarkRepository {

    CourseInBookmark save(CourseInBookmark courseInBookmark);

    CourseInBookmark findById(Long courseInBookmarkId);

    CourseInBookmark findByCourseBookmarkIdAndCourseId(Long courseBookmarkId, Long courseId);

    void deleteById(Long courseInBookmarkId);

}
