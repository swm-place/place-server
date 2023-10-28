package kr.yeoksi.ours.oursserver.magazine.service;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.exception.NotExistedPlaceInCourseMagazineException;
import kr.yeoksi.ours.oursserver.magazine.exception.PlaceWrongReferenceException;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineService;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.PlaceInCourseMagazineService;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.PlaceInCourseMagazineRepository;
import kr.yeoksi.ours.oursserver.others.exception.NotExistedPlaceException;
import kr.yeoksi.ours.oursserver.others.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PlaceInCourseMagazineServiceImpl implements PlaceInCourseMagazineService {

    private final PlaceInCourseMagazineRepository placeInCourseMagazineRepository;

    private final CourseMagazineService courseMagazineService;
    private final PlaceService placeService;


    @Override
    @Transactional
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
    @Transactional
    public PlaceInCourseMagazine update(PlaceInCourseMagazine placeInCourseMagazine, Long magazineId, String userId) {
        // validate right magazine and owner
        CourseMagazine magazine = courseMagazineService.getById(magazineId);

        // validate existed
        PlaceInCourseMagazine toUpdate = placeInCourseMagazineRepository.findById(placeInCourseMagazine.getId())
                .orElseThrow(NotExistedPlaceInCourseMagazineException::new);

        // validate right place and sync place info
        try {
            placeInCourseMagazine.setPlace(
                    placeService.findById(placeInCourseMagazine.getPlace().getId()));
        }
        catch (NotExistedPlaceException e) {
            throw new PlaceWrongReferenceException();
        }

        // update placeInCourseMagazine
        toUpdate.update(placeInCourseMagazine);
        return placeInCourseMagazineRepository.save(placeInCourseMagazine, magazine);
    }

    @Override
    @Transactional
    public void delete(Long id, Long magazineId, String userId) {
        // validate existed
        PlaceInCourseMagazine toDelete = placeInCourseMagazineRepository.findById(id)
                .orElseThrow(NotExistedPlaceInCourseMagazineException::new);

        // validate right magazine and owner
        CourseMagazine magazine = courseMagazineService.getById(magazineId);
        if (!magazine.getId().equals(magazineId)) {
            throw new NotExistedPlaceInCourseMagazineException();
        }

        // delete placeInCourseMagazine
        placeInCourseMagazineRepository.delete(id);
    }

    @Override
    @Transactional
    public PlaceInCourseMagazine getById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public List<PlaceInCourseMagazine> findByMagazineId(Long magazineId) {
        return null;
    }
}
