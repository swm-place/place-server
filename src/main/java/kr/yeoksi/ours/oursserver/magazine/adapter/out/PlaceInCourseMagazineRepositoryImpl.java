package kr.yeoksi.ours.oursserver.magazine.adapter.out;

import kr.yeoksi.ours.oursserver.magazine.adapter.out.entity.CourseMagazineJpaEntity;
import kr.yeoksi.ours.oursserver.magazine.adapter.out.entity.PlaceInCourseMagazineJpaEntity;
import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.PlaceInCourseMagazineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class PlaceInCourseMagazineRepositoryImpl implements PlaceInCourseMagazineRepository {

    private PlaceInCourseMagazineJpaRepository placeInCourseMagazineJpaRepository;
    private CourseMagazineJpaRepository courseMagazineJpaRepository;


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
        Optional<PlaceInCourseMagazineJpaEntity> placeInCourseMagazine = placeInCourseMagazineJpaRepository.findById(id);
        if (placeInCourseMagazine.isEmpty()) return;

        placeInCourseMagazine.get().getCourseMagazine().removePlaceInCourseMagazine(placeInCourseMagazine.get());

        placeInCourseMagazineJpaRepository.delete(placeInCourseMagazine.get());
    }
}
