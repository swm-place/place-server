package kr.yeoksi.ours.oursserver.course.adapter.out;

import kr.yeoksi.ours.oursserver.course.adapter.out.entity.PlaceInCourseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceInCourseJpaRepository extends JpaRepository<PlaceInCourseJpaEntity, Long> {
}
