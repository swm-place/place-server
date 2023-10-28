package kr.yeoksi.ours.oursserver.magazine.service;

import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.PlaceInCourseMagazineService;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.PlaceInCourseMagazineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PlaceInCourseMagazineServiceImpl implements PlaceInCourseMagazineService {

    private final PlaceInCourseMagazineRepository placeInCourseMagazineRepository;


    @Override
    public PlaceInCourseMagazine append(PlaceInCourseMagazine placeInCourseMagazine, Long magazineId, String userId) {
        return null;
    }

    @Override
    public PlaceInCourseMagazine update(PlaceInCourseMagazine placeInCourseMagazine, Long magazineId, String userId) {
        return null;
    }

    @Override
    public void delete(Long id, Long magazineId, String userId) {

    }

    @Override
    public PlaceInCourseMagazine getById(Long id) {
        return null;
    }

    @Override
    public List<PlaceInCourseMagazine> findByMagazineId(Long magazineId) {
        return null;
    }
}
