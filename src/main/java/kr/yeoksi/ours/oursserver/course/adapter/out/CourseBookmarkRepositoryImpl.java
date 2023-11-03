package kr.yeoksi.ours.oursserver.course.adapter.out;

import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.CourseBookmarkJpaRepository;
import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseBookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CourseBookmarkRepositoryImpl implements CourseBookmarkRepository {

    private final CourseBookmarkJpaRepository courseBookmarkJpaRepository;


    @Override
    public CourseBookmark save(CourseBookmark courseBookmark) {
        return null;
    }

    @Override
    public Optional<CourseBookmark> findById(Long courseBookmarkId) {
        return Optional.empty();
    }

    @Override
    public List<CourseBookmark> findByUserId(String userId, int page, int size) {
        return null;
    }

    @Override
    public void deleteById(Long courseBookmarkId) {

    }
}
