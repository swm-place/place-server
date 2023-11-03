package kr.yeoksi.ours.oursserver.course.adapter.out;

import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.CourseBookmarkJpaRepository;
import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity.CourseBookmarkJpaEntity;
import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseBookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CourseBookmarkRepositoryImpl implements CourseBookmarkRepository {

    private final CourseBookmarkJpaRepository courseBookmarkJpaRepository;


    @Override
    public CourseBookmark save(CourseBookmark courseBookmark) {
        CourseBookmarkJpaEntity courseBookmarkJpaEntity = CourseBookmarkJpaEntity.from(courseBookmark);
        return courseBookmarkJpaRepository.save(courseBookmarkJpaEntity).toBookmark();
    }

    @Override
    public Optional<CourseBookmark> findById(Long courseBookmarkId) {
        return courseBookmarkJpaRepository.findById(courseBookmarkId)
                .map(CourseBookmarkJpaEntity::toBookmark);
    }

    @Override
    public List<CourseBookmark> findByUserId(String userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return courseBookmarkJpaRepository.findByUserId(userId, pageRequest).stream()
                .map(CourseBookmarkJpaEntity::toBookmark)
                .toList();
    }

    @Override
    public void deleteById(Long courseBookmarkId) {
        courseBookmarkJpaRepository.deleteById(courseBookmarkId);
    }
}
