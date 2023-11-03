package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseBookmarkService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseBookmarkServiceImpl implements CourseBookmarkService {
    @Override
    public CourseBookmark createCourseBookmark(CourseBookmark courseBookmark, String userId) {
        return null;
    }

    @Override
    public CourseBookmark getCourseBookmark(Long courseBookmarkId, String userId) {
        return null;
    }

    @Override
    public List<CourseBookmark> getMyCourseBookmarks(String userId, int page, int size) {
        return null;
    }

    @Override
    public CourseBookmark updateCourseBookmark(CourseBookmark courseBookmark, String userId) {
        return null;
    }

    @Override
    public void deleteCourseBookmark(Long courseBookmarkId, String userId) {

    }
}
