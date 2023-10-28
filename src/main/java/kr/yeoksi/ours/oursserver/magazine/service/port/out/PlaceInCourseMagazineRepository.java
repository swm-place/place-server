package kr.yeoksi.ours.oursserver.magazine.service.port.out;

import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;

import java.util.List;

public interface PlaceInCourseMagazineRepository {

    PlaceInCourseMagazine save(PlaceInCourseMagazine placeInCourseMagazine);

    PlaceInCourseMagazine findById(Long id);

    List<PlaceInCourseMagazine> findByMagazineId(Long magazineId);

    void delete(Long id);

}
