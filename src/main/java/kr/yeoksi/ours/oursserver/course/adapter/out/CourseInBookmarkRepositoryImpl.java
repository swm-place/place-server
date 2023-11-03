package kr.yeoksi.ours.oursserver.course.adapter.out;

import kr.yeoksi.ours.oursserver.course.domain.CourseInBookmark;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseInBookmarkRepository;

import java.util.List;
import java.util.Optional;

public class CourseInBookmarkRepositoryImpl implements CourseInBookmarkRepository {
    @Override
    public CourseInBookmark save(CourseInBookmark courseInBookmark) {
        return null;
    }

    @Override
    public Optional<CourseInBookmark> findById(Long courseInBookmarkId) {
        return Optional.empty();
    }

    @Override
    public Optional<CourseInBookmark> findByCourseBookmarkIdAndCourseId(Long courseBookmarkId, Long courseId) {
        return Optional.empty();
    }

    @Override
    public boolean existsByCourseBookmarkIdAndCourseId(Long courseBookmarkId, Long courseId) {
        return false;
    }

    @Override
    public void deleteById(Long courseInBookmarkId) {

    }

    @Override
    public List<CourseInBookmark> findByCourseId(Long courseId) {
        return null;
    }
}
