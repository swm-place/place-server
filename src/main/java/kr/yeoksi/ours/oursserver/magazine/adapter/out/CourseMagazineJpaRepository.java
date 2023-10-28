package kr.yeoksi.ours.oursserver.magazine.adapter.out;

import kr.yeoksi.ours.oursserver.magazine.adapter.out.entity.CourseMagazineJpaEntity;
import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseMagazineJpaRepository extends JpaRepository<CourseMagazineJpaEntity, Long> {
}
