package kr.yeoksi.ours.oursserver.course.service.port;

import kr.yeoksi.ours.oursserver.course.service.port.in.CourseInBookmarkService;
import org.springframework.stereotype.Service;


@Service
public class CourseInBookmarkServiceImpl implements CourseInBookmarkService {
    @Override
    public void addCourseToBookmark(Long courseBookmarkId, Long courseId, String userId) {

    }

    @Override
    public void deleteCourseInBookmark(Long courseBookmarkId, Long courseId, String userId) {

    }
}
