package kr.yeoksi.ours.oursserver.course.service.port.out;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;

import java.util.List;

public interface CourseBookmarkRepository {

    CourseBookmark save(CourseBookmark courseBookmark);

    CourseBookmark findById(Long courseBookmarkId);

    List<CourseBookmark> findByUserId(String userId, int page, int size);

    void deleteById(Long courseBookmarkId);

}
