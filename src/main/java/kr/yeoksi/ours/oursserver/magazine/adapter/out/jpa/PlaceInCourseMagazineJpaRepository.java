package kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa;

import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.entity.PlaceInCourseMagazineJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceInCourseMagazineJpaRepository extends JpaRepository<PlaceInCourseMagazineJpaEntity, Long> {

    List<PlaceInCourseMagazineJpaEntity> findByCourseMagazineId(Long magazineId);

}
