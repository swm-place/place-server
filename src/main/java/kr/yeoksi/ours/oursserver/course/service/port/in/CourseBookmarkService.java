package kr.yeoksi.ours.oursserver.course.service.port.in;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;

public interface CourseBookmarkService {

    CourseBookmark createCourseBookmark(CourseBookmark courseBookmark);

    CourseBookmark getCourseBookmark(Long courseBookmarkId);

    CourseBookmark updateCourseBookmark(CourseBookmark courseBookmark);

    void deleteCourseBookmark(Long courseBookmarkId);

}
