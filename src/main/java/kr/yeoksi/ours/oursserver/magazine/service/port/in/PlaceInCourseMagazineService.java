package kr.yeoksi.ours.oursserver.magazine.service.port.in;

import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;

import java.util.List;

public interface PlaceInCourseMagazineService {

    PlaceInCourseMagazine append(PlaceInCourseMagazine placeInCourseMagazine, Long magazineId, String userId);

    PlaceInCourseMagazine update(PlaceInCourseMagazine placeInCourseMagazine, Long magazineId, String userId);

    void delete(Long id, Long magazineId, String userId);

    PlaceInCourseMagazine getById(Long id);

    List<PlaceInCourseMagazine> findByMagazineId(Long magazineId);

}
