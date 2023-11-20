package kr.yeoksi.ours.oursserver.course.adapter.out.jpa;

import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity.CourseJpaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseJpaRepository extends JpaRepository<CourseJpaEntity, Long> {

    List<CourseJpaEntity> findAllByUserIdOrderByCreatedAtDesc(String userId, Pageable pageable);

}
