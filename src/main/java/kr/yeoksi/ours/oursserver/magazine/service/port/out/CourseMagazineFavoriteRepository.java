package kr.yeoksi.ours.oursserver.magazine.service.port.out;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazineFavorite;

import java.util.Optional;

public interface CourseMagazineFavoriteRepository {

    CourseMagazineFavorite save(CourseMagazineFavorite courseMagazineFavorite);

    Optional<CourseMagazineFavorite> findByUserIdAndCourseMagazineId(Long userId, Long courseMagazineId);

    void delete(CourseMagazineFavorite courseMagazineFavorite);

    boolean existsByUserIdAndCourseMagazineId(String userId, Long courseMagazineId);

}
