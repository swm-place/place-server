package kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa;

import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.entity.CourseMagazineJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseMagazineJpaRepository extends JpaRepository<CourseMagazineJpaEntity, Long> {
}
