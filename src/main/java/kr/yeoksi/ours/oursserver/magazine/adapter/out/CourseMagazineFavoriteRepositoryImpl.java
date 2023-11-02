package kr.yeoksi.ours.oursserver.magazine.adapter.out;

import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.CourseMagazineFavoriteJpaRepository;
import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.entity.CourseMagazineFavoriteJpaEntity;
import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazineFavorite;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.CourseMagazineFavoriteRepository;
import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CourseMagazineFavoriteRepositoryImpl implements CourseMagazineFavoriteRepository {

    private final CourseMagazineFavoriteJpaRepository courseMagazineFavoriteJpaRepository;


    @Override
    public CourseMagazineFavorite save(CourseMagazineFavorite courseMagazineFavorite) {
        CourseMagazineFavoriteJpaEntity toSave = CourseMagazineFavoriteJpaEntity.from(courseMagazineFavorite);
        return courseMagazineFavoriteJpaRepository.save(toSave)
                .toFavorite();
    }

    @Override
    public Optional<CourseMagazineFavorite> findByUserIdAndCourseMagazineId(String userId, Long courseMagazineId) {
        return courseMagazineFavoriteJpaRepository.findByUserIdAndCourseMagazineId(userId, courseMagazineId)
                .map(CourseMagazineFavoriteJpaEntity::toFavorite);
    }

    @Override
    public void delete(CourseMagazineFavorite courseMagazineFavorite) {
        CourseMagazineFavoriteJpaEntity toDelete = CourseMagazineFavoriteJpaEntity.from(courseMagazineFavorite);
        courseMagazineFavoriteJpaRepository.delete(toDelete);
    }

    @Override
    public boolean existsByUserIdAndCourseMagazineId(String userId, Long courseMagazineId) {
        return findByUserIdAndCourseMagazineId(userId, courseMagazineId).isPresent();
    }
}
