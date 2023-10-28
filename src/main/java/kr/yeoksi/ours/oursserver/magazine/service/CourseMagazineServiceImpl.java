package kr.yeoksi.ours.oursserver.magazine.service;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.exception.DuplicatedCourseMagazineException;
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
    public CourseMagazine publish(CourseMagazine courseMagazine) {
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
    public CourseMagazine findById(Long id) {
        return null;
    }

    @Override
    public List<CourseMagazine> findLatestCourseMagazines(int count) {
        return null;
    }

    @Override
    public CourseMagazine update(CourseMagazine courseMagazine) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
