package kr.yeoksi.ours.oursserver.course.adapter.out.jpa;

import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity.CourseBookmarkJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseBookmarkJpaRepository extends JpaRepository<CourseBookmarkJpaEntity, Long> {
}
