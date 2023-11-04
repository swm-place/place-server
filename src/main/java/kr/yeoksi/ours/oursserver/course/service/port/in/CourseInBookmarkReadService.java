package kr.yeoksi.ours.oursserver.course.service.port.in;

import kr.yeoksi.ours.oursserver.course.domain.CourseInBookmark;

import java.util.List;

public interface CourseInBookmarkReadService {

    List<CourseInBookmark> findByCourseId(Long courseId, String userId);

}
