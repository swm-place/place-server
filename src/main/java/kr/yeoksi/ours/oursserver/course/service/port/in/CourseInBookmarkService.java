package kr.yeoksi.ours.oursserver.course.service.port.in;

public interface CourseInBookmarkService {

    void addCourseToBookmark(Long courseBookmarkId, Long courseId, String userId);

    void deleteCourseInBookmark(Long courseBookmarkId, Long courseId, String userId);

}
