package kr.yeoksi.ours.oursserver.course.adapter.out.jpa;

import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity.CourseBookmarkJpaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseBookmarkJpaRepository extends JpaRepository<CourseBookmarkJpaEntity, Long> {

    List<CourseBookmarkJpaEntity> findByUserId(String userId, Pageable pageable);

}
