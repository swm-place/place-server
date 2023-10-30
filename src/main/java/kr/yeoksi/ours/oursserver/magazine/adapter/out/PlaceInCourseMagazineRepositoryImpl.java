package kr.yeoksi.ours.oursserver.magazine.adapter.out;

import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.entity.CourseMagazineJpaEntity;
import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.entity.PlaceInCourseMagazineJpaEntity;
import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.CourseMagazineJpaRepository;
import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.PlaceInCourseMagazineJpaRepository;
import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.exception.NotExistedCourseMagazineException;
import kr.yeoksi.ours.oursserver.magazine.exception.NotExistedPlaceInCourseMagazineException;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.PlaceInCourseMagazineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class PlaceInCourseMagazineRepositoryImpl implements PlaceInCourseMagazineRepository {

    private final PlaceInCourseMagazineJpaRepository placeInCourseMagazineJpaRepository;
    private final CourseMagazineJpaRepository courseMagazineJpaRepository;


    @Override
    public PlaceInCourseMagazine save(PlaceInCourseMagazine placeInCourseMagazine, CourseMagazine courseMagazine) {
        CourseMagazineJpaEntity courseMagazineJpaEntity = CourseMagazineJpaEntity.from(courseMagazine);
        PlaceInCourseMagazineJpaEntity placeInCourseMagazineJpaEntity =
                PlaceInCourseMagazineJpaEntity.from(placeInCourseMagazine, courseMagazineJpaEntity);

        courseMagazineJpaEntity.addOrUpdatePlaceInCourseMagazine(placeInCourseMagazineJpaEntity);
        return placeInCourseMagazineJpaRepository.save(placeInCourseMagazineJpaEntity).toPlaceInCourseMagazine();
    }

    @Override
    public Optional<PlaceInCourseMagazine> findById(Long id) {
        return placeInCourseMagazineJpaRepository.findById(id)
                .map(PlaceInCourseMagazineJpaEntity::toPlaceInCourseMagazine);
    }

    @Override
    public List<PlaceInCourseMagazine> findByMagazineId(Long magazineId) {
        return placeInCourseMagazineJpaRepository.findByCourseMagazineId(magazineId)
                .stream()
                .map(PlaceInCourseMagazineJpaEntity::toPlaceInCourseMagazine)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Optional<PlaceInCourseMagazineJpaEntity> found = placeInCourseMagazineJpaRepository.findById(id);
        if (found.isEmpty()) return;

        CourseMagazineJpaEntity courseMagazineToSave = courseMagazineJpaRepository.findById(found.get().getCourseMagazine().getId())
                        .orElse(courseMagazineJpaRepository.save(found.get().getCourseMagazine()));

        courseMagazineToSave.removePlaceInCourseMagazine(found.get().getId());
        courseMagazineJpaRepository.save(courseMagazineToSave);

        placeInCourseMagazineJpaRepository.deleteById(id);
    }
}
