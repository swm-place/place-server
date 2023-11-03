package kr.yeoksi.ours.oursserver.course.service.port.in;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;

import java.util.List;

public interface CourseBookmarkService {

    CourseBookmark createCourseBookmark(CourseBookmark courseBookmark);

    CourseBookmark getCourseBookmark(Long courseBookmarkId);

    List<CourseBookmark> getMyCourseBookmarks(Long userId, int page, int size);

    CourseBookmark updateCourseBookmark(CourseBookmark courseBookmark);

    void deleteCourseBookmark(Long courseBookmarkId);

}
