package kr.yeoksi.ours.oursserver.magazine.service;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.exception.PlaceWrongReferenceException;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineService;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.PlaceInCourseMagazineService;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.PlaceInCourseMagazineRepository;
import kr.yeoksi.ours.oursserver.others.exception.NotExistedPlaceException;
import kr.yeoksi.ours.oursserver.others.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PlaceInCourseMagazineServiceImpl implements PlaceInCourseMagazineService {

    private final PlaceInCourseMagazineRepository placeInCourseMagazineRepository;

    private final CourseMagazineService courseMagazineService;
    private final PlaceService placeService;


    @Override
    public PlaceInCourseMagazine append(PlaceInCourseMagazine placeInCourseMagazine, Long magazineId, String userId) {
        // validate right magazine and owner
        CourseMagazine magazine = courseMagazineService.getById(magazineId);

        // validate right place and sync place info
        try {
            placeInCourseMagazine.setPlace(
                    placeService.findById(placeInCourseMagazine.getPlace().getId()));
        }
        catch (NotExistedPlaceException e) {
            // TODO: Place 관련 예외 처리 ServiceException으로 통합
            throw new PlaceWrongReferenceException();
        }

        // append placeInCourseMagazine
        placeInCourseMagazine.setId(null);
        return placeInCourseMagazineRepository.save(placeInCourseMagazine, magazine);
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
