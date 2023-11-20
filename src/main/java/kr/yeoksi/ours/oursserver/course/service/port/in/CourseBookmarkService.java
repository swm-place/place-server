package kr.yeoksi.ours.oursserver.course.service.port.in;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;

import java.util.List;

public interface CourseBookmarkService {

    CourseBookmark createCourseBookmark(CourseBookmark courseBookmark, String userId);

    CourseBookmark getCourseBookmark(Long courseBookmarkId, String userId, int coursePage, int courseSize);

    List<CourseBookmark> getMyCourseBookmarks(String userId, int page, int size);

    CourseBookmark updateCourseBookmark(CourseBookmark courseBookmark, String userId);

    void deleteCourseBookmark(Long courseBookmarkId, String userId);

}
