package kr.yeoksi.ours.oursserver.course.adapter.out;

import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.CourseInBookmarkJpaRepository;
import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity.CourseInBookmarkJpaEntity;
import kr.yeoksi.ours.oursserver.course.domain.CourseInBookmark;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseInBookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CourseInBookmarkRepositoryImpl implements CourseInBookmarkRepository {

    private final CourseInBookmarkJpaRepository courseInBookmarkJpaRepository;


    @Override
    public CourseInBookmark save(CourseInBookmark courseInBookmark) {
        CourseInBookmarkJpaEntity courseInBookmarkJpaEntity = CourseInBookmarkJpaEntity.from(courseInBookmark);
        return courseInBookmarkJpaRepository.save(courseInBookmarkJpaEntity)
                .toCourseInBookmark();
    }

    @Override
    public Optional<CourseInBookmark> findById(Long courseInBookmarkId) {
        return courseInBookmarkJpaRepository.findById(courseInBookmarkId)
                .map(CourseInBookmarkJpaEntity::toCourseInBookmark);
    }

    @Override
    public List<CourseInBookmark> findByCourseBookmarkId(Long courseBookmarkId) {
        Pageable pageable = Pageable.unpaged();

        return courseInBookmarkJpaRepository.findByCourseBookmarkId(courseBookmarkId, pageable).stream()
                .map(CourseInBookmarkJpaEntity::toCourseInBookmark)
                .toList();
    }

    @Override
    public List<CourseInBookmark> findByCourseBookmarkIdWithPage(Long courseBookmarkId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return courseInBookmarkJpaRepository.findByCourseBookmarkId(courseBookmarkId, pageRequest).stream()
                .map(CourseInBookmarkJpaEntity::toCourseInBookmark)
                .toList();
    }

    @Override
    public Optional<CourseInBookmark> findByCourseBookmarkIdAndCourseId(Long courseBookmarkId, Long courseId) {
        return courseInBookmarkJpaRepository.findByCourseBookmarkIdAndCourseId(courseBookmarkId, courseId)
                .stream()
                .findFirst()
                .map(CourseInBookmarkJpaEntity::toCourseInBookmark);
    }

    @Override
    public boolean existsByCourseBookmarkIdAndCourseId(Long courseBookmarkId, Long courseId) {
        return courseInBookmarkJpaRepository.existsByCourseBookmarkIdAndCourseId(courseBookmarkId, courseId);
    }

    @Override
    public void deleteById(Long courseInBookmarkId) {
        courseInBookmarkJpaRepository.deleteById(courseInBookmarkId);
    }

    @Override
    public List<CourseInBookmark> findByCourseId(Long courseId) {
        return courseInBookmarkJpaRepository.findByCourseId(courseId).stream()
                .map(CourseInBookmarkJpaEntity::toCourseInBookmark)
                .toList();
    }
}
