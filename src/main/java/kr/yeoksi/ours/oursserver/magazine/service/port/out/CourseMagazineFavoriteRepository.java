package kr.yeoksi.ours.oursserver.magazine.service.port.out;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazineFavorite;

import java.util.List;
import java.util.Optional;

public interface CourseMagazineFavoriteRepository {

    CourseMagazineFavorite save(CourseMagazineFavorite courseMagazineFavorite);

    Optional<CourseMagazineFavorite> findByUserIdAndCourseMagazineId(String userId, Long courseMagazineId);

    void delete(CourseMagazineFavorite courseMagazineFavorite);

    boolean existsByUserIdAndCourseMagazineId(String userId, Long courseMagazineId);

    List<CourseMagazine> findCourseMagazinesByUserId(String userId);

}
