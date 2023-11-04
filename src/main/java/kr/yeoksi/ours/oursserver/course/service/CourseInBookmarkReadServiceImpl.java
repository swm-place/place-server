package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.CourseInBookmark;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseInBookmarkReadService;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseInBookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseInBookmarkReadServiceImpl implements CourseInBookmarkReadService {

    private final CourseInBookmarkRepository courseInBookmarkRepository;


    @Override
    public List<CourseInBookmark> findByCourseId(Long courseId, String userId) {
        List<CourseInBookmark> courseInBookmarks = courseInBookmarkRepository.findByCourseId(courseId);

        courseInBookmarks = courseInBookmarks.stream()
                .filter(courseInBookmark -> userId.equals(courseInBookmark.getCourseBookmark().getUser().getId()))
                .toList();

        return courseInBookmarks;
    }

}
