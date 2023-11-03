package kr.yeoksi.ours.oursserver.course.service.port;

import kr.yeoksi.ours.oursserver.course.service.port.in.CourseInBookmarkService;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseInBookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CourseInBookmarkServiceImpl implements CourseInBookmarkService {

    private final CourseInBookmarkRepository courseInBookmarkRepository;


    @Override
    public void addCourseToBookmark(Long courseBookmarkId, Long courseId, String userId) {

    }

    @Override
    public void deleteCourseInBookmark(Long courseBookmarkId, Long courseId, String userId) {

    }
}
