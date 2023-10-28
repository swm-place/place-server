package kr.yeoksi.ours.oursserver.magazine.service;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.exception.DuplicatedCourseMagazineException;
import kr.yeoksi.ours.oursserver.magazine.exception.NoPermissionOfCourseMagazineException;
import kr.yeoksi.ours.oursserver.magazine.exception.NotExistedCourseMagazineException;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineService;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.CourseMagazineRepository;
import kr.yeoksi.ours.oursserver.others.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseMagazineServiceImpl implements CourseMagazineService {

    private CourseMagazineRepository courseMagazineRepository;

    private PlaceService placeService;


    @Override
    public CourseMagazine publish(CourseMagazine courseMagazine, String userId) {
        // validate duplicated
        Optional<CourseMagazine> found = courseMagazineRepository.findById(courseMagazine.getId());
        if (found.isPresent()) {
            throw new DuplicatedCourseMagazineException();
        }

        // sync place, user
        courseMagazine.setPlacesInCourseMagazine(
                courseMagazine.getPlacesInCourseMagazine().stream()
                        .peek(placeInCourseMagazine -> placeInCourseMagazine.setPlace(
                                placeService.findById(placeInCourseMagazine.getPlace().getId())))
                        .toList());

        courseMagazine.setUser(courseMagazine.getUser());

        // save
        return courseMagazineRepository.save(courseMagazine);
    }

    @Override
    public CourseMagazine getById(Long id) {
        Optional<CourseMagazine> courseMagazine = courseMagazineRepository.findById(id);
        if (courseMagazine.isEmpty()) {
            throw new NotExistedCourseMagazineException();
        }

        return courseMagazine.get();
    }

    @Override
    public List<CourseMagazine> findLatestCourseMagazines(int count) {
        // TODO: implement
        return null;
    }

    @Override
    public CourseMagazine update(CourseMagazine courseMagazine, String userId) {
        CourseMagazine toUpdate = getById(courseMagazine.getId());

        // validate ownership
        if (!toUpdate.getUser().getId().equals(courseMagazine.getUser().getId())) {
            throw new NoPermissionOfCourseMagazineException();
        }

        // sync place
        courseMagazine.setPlacesInCourseMagazine(
                courseMagazine.getPlacesInCourseMagazine().stream()
                        .peek(placeInCourseMagazine -> placeInCourseMagazine.setPlace(
                                placeService.findById(placeInCourseMagazine.getPlace().getId())))
                        .toList());

        // TODO: validate ownership of places in course magazine and save

        return courseMagazineRepository.save(courseMagazine);
    }

    @Override
    public void delete(Long id, String userId) {

    }
}
