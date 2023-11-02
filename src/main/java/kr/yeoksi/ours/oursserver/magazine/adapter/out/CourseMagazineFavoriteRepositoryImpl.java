package kr.yeoksi.ours.oursserver.magazine.adapter.out;

import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.CourseMagazineFavoriteJpaRepository;
import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazineFavorite;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.CourseMagazineFavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CourseMagazineFavoriteRepositoryImpl implements CourseMagazineFavoriteRepository {

    private final CourseMagazineFavoriteJpaRepository courseMagazineFavoriteJpaRepository;


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
