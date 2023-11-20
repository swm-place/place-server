package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import kr.yeoksi.ours.oursserver.course.exception.NotExistedCourseBookmarkException;
import kr.yeoksi.ours.oursserver.course.exception.NotOwnerOfCourseBookmarkException;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseBookmarkService;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseBookmarkRepository;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseInBookmarkRepository;
import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseBookmarkServiceImpl implements CourseBookmarkService {

    private final CourseBookmarkRepository courseBookmarkRepository;

    private final CourseInBookmarkRepository courseInBookmarkRepository;


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

        courseBookmark.setCoursesInBookmark(
                courseInBookmarkRepository.findByCourseBookmarkId(courseBookmarkId));

        return courseBookmark;
    }

    @Override
    public CourseBookmark getCourseBookmarkWithCoursePage(Long courseBookmarkId, String userId, int page, int size) {
        CourseBookmark courseBookmark = courseBookmarkRepository.findById(courseBookmarkId)
                .orElseThrow(NotExistedCourseBookmarkException::new);

        if (!userId.equals(courseBookmark.getUser().getId())) {
            throw new NotOwnerOfCourseBookmarkException();
        }

        courseBookmark.setCoursesInBookmark(
                courseInBookmarkRepository.findByCourseBookmarkIdWithPage(courseBookmarkId, page, size));

        return courseBookmark;
    }

    @Override
    public List<CourseBookmark> getMyCourseBookmarks(String userId, int page, int size) {
        // TODO: 쿼리 여러 번 나가지 않고 한 번에 가져올 수 있도록 연관관계 동기화 문제 재점검
        return courseBookmarkRepository.findByUserId(userId, page, size).stream()
                .peek(courseBookmark -> courseBookmark.setCoursesInBookmark(
                        courseInBookmarkRepository.findByCourseBookmarkId(courseBookmark.getId())))
                .toList();
    }

    @Override
    public CourseBookmark updateCourseBookmark(CourseBookmark courseBookmark, String userId) {
        CourseBookmark toUpdate = courseBookmarkRepository.findById(courseBookmark.getId())
                .orElseThrow(NotExistedCourseBookmarkException::new);

        if (!userId.equals(toUpdate.getUser().getId())) {
            throw new NotOwnerOfCourseBookmarkException();
        }

        // TODO: 도메인 객체로 업데이트 로직 분리
        if (courseBookmark.getTitle() != null)
            toUpdate.setTitle(courseBookmark.getTitle());

        return courseBookmarkRepository.save(toUpdate);
    }

    @Override
    public void deleteCourseBookmark(Long courseBookmarkId, String userId) {
        CourseBookmark toDelete = courseBookmarkRepository.findById(courseBookmarkId)
                .orElseThrow(NotExistedCourseBookmarkException::new);

        if (!userId.equals(toDelete.getUser().getId())) {
            throw new NotOwnerOfCourseBookmarkException();
        }

        courseBookmarkRepository.deleteById(courseBookmarkId);
    }
}
