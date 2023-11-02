package kr.yeoksi.ours.oursserver.magazine.adapter.out;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazineFavorite;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.CourseMagazineFavoriteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class CourseMagazineFavoriteRepositoryImpl implements CourseMagazineFavoriteRepository {
    @Override
    public CourseMagazineFavorite save(CourseMagazineFavorite courseMagazineFavorite) {
        return null;
    }

    @Override
    public Optional<CourseMagazineFavorite> findByUserIdAndCourseMagazineId(Long userId, Long courseMagazineId) {
        return Optional.empty();
    }

    @Override
    public void delete(CourseMagazineFavorite courseMagazineFavorite) {

    }

    @Override
    public boolean existsByUserIdAndCourseMagazineId(Long userId, Long courseMagazineId) {
        return false;
    }
}
