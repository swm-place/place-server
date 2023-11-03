package kr.yeoksi.ours.oursserver.course.adapter.out.jpa;

import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity.CourseInBookmarkJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseInBookmarkJpaRepository extends JpaRepository<CourseInBookmarkJpaEntity, Long> {
}
