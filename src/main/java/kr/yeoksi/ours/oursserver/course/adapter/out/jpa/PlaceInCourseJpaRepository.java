package kr.yeoksi.ours.oursserver.course.adapter.out.jpa;

import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity.PlaceInCourseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceInCourseJpaRepository extends JpaRepository<PlaceInCourseJpaEntity, Long> {

    List<PlaceInCourseJpaEntity> findAllByCourseId(Long courseId);

    List<PlaceInCourseJpaEntity> findAllByCourseIdOrderByVisitOrderAsc(Long courseId);

}
