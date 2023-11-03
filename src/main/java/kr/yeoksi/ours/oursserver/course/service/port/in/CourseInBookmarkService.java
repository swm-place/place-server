package kr.yeoksi.ours.oursserver.course.service.port.in;

import kr.yeoksi.ours.oursserver.course.domain.CourseInBookmark;

import java.util.List;

public interface CourseInBookmarkService {

    void addCourseToBookmark(Long courseBookmarkId, Long courseId, String userId);

    void deleteCourseInBookmark(Long courseBookmarkId, Long courseId, String userId);

    List<CourseInBookmark> findByCourseId(Long courseId, String userId);

}
