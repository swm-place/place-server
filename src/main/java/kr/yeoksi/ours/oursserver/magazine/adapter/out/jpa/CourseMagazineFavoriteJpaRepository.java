package kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa;

import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.entity.CourseMagazineFavoriteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseMagazineFavoriteJpaRepository extends JpaRepository<CourseMagazineFavoriteJpaEntity, Long> {

    List<CourseMagazineFavoriteJpaEntity> findByUserId(String userId);

    List<CourseMagazineFavoriteJpaEntity> findByCourseMagazineId(Long courseMagazineId);
}
