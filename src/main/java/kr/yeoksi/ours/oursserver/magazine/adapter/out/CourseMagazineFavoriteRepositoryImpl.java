package kr.yeoksi.ours.oursserver.magazine.adapter.out;

import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.CourseMagazineFavoriteJpaRepository;
import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.entity.CourseMagazineFavoriteJpaEntity;
import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazineFavorite;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.CourseMagazineFavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        return courseMagazineFavoriteJpaRepository.findDistinctFirstByUserIdAndCourseMagazineId(userId, courseMagazineId)
                .map(CourseMagazineFavoriteJpaEntity::toFavorite);
    }

    @Override
    public void deleteById(Long id) {
        courseMagazineFavoriteJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByUserIdAndCourseMagazineId(String userId, Long courseMagazineId) {
        return findByUserIdAndCourseMagazineId(userId, courseMagazineId).isPresent();
    }

    @Override
    public List<CourseMagazineFavorite> findByUserId(String userId) {
        return courseMagazineFavoriteJpaRepository.findByUserId(userId)
                .stream()
                .map(CourseMagazineFavoriteJpaEntity::toFavorite)
                .toList();
    }

    @Override
    public List<CourseMagazineFavorite> findByUserIdWithPage(String userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return courseMagazineFavoriteJpaRepository.findByUserId(userId, pageRequest)
                .stream()
                .map(CourseMagazineFavoriteJpaEntity::toFavorite)
                .toList();
    }
}
