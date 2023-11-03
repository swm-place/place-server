package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import kr.yeoksi.ours.oursserver.course.exception.NotExistedCourseBookmarkException;
import kr.yeoksi.ours.oursserver.course.exception.NotOwnerOfCourseBookmarkException;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseBookmarkService;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseBookmarkRepository;
import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseBookmarkServiceImpl implements CourseBookmarkService {

    private final CourseBookmarkRepository courseBookmarkRepository;


    @Override
    public CourseBookmark createCourseBookmark(CourseBookmark courseBookmark, String userId) {
        courseBookmark.setUser(
                User.builder().id(userId).build());

        return courseBookmarkRepository.save(courseBookmark);
    }

    @Override
    public CourseBookmark getCourseBookmark(Long courseBookmarkId, String userId) {
        CourseBookmark courseBookmark = courseBookmarkRepository.findById(courseBookmarkId)
                .orElseThrow(NotExistedCourseBookmarkException::new);

        if (!userId.equals(courseBookmark.getUser().getId())) {
            throw new NotOwnerOfCourseBookmarkException();
        }

        return courseBookmark;
    }

    @Override
    public List<CourseBookmark> getMyCourseBookmarks(String userId, int page, int size) {
        return courseBookmarkRepository.findByUserId(userId, page, size);
    }

    @Override
    public CourseBookmark updateCourseBookmark(CourseBookmark courseBookmark, String userId) {
        return null;
    }

    @Override
    public void deleteCourseBookmark(Long courseBookmarkId, String userId) {

    }
}
