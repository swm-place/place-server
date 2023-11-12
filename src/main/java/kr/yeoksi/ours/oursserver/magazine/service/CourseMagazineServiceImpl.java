package kr.yeoksi.ours.oursserver.magazine.service;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.exception.DuplicatedCourseMagazineException;
import kr.yeoksi.ours.oursserver.magazine.exception.NoPermissionOfCourseMagazineException;
import kr.yeoksi.ours.oursserver.magazine.exception.NotExistedCourseMagazineException;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineFavoriteService;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineService;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.CourseMagazineRepository;
import kr.yeoksi.ours.oursserver.others.service.PlaceService;
import kr.yeoksi.ours.oursserver.others.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseMagazineServiceImpl implements CourseMagazineService {

    private final CourseMagazineRepository courseMagazineRepository;

    private final CourseMagazineFavoriteService courseMagazineFavoriteService;
    private final PlaceService placeService;
    private final UserService userService;


    @Override
    @Transactional
    public CourseMagazine publish(CourseMagazine courseMagazine, String userId) {
        // validate duplicated
        if (courseMagazine.getId() != null && courseMagazineRepository.findById(courseMagazine.getId()).isPresent()) {
            throw new DuplicatedCourseMagazineException();
        }

        // sync place, user
        courseMagazine.setPlacesInCourseMagazine(
                courseMagazine.getPlacesInCourseMagazine().stream()
                        .peek(placeInCourseMagazine -> placeInCourseMagazine.setPlace(
                                placeService.findById(placeInCourseMagazine.getPlace().getId())))
                        .toList());

        courseMagazine.setUser(userService.findById(userId));

        // save
        return courseMagazineRepository.save(courseMagazine);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseMagazine getById(Long id) {
        if (id == null) throw new NotExistedCourseMagazineException();

        Optional<CourseMagazine> courseMagazine = courseMagazineRepository.findById(id);
        if (courseMagazine.isEmpty()) {
            throw new NotExistedCourseMagazineException();
        }

        return courseMagazine.get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseMagazine> findLatestCourseMagazines(int count, int page) {
        // TODO: 범위 초과된 경우 체크
        return courseMagazineRepository.findPageOrderByCreatedAtDesc(count, page);
    }

    @Override
    @Transactional
    public CourseMagazine update(CourseMagazine courseMagazine, String userId) {
        CourseMagazine toUpdate = getById(courseMagazine.getId());

        // validate ownership
        if (!toUpdate.getUser().getId().equals(userId)) {
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
    @Transactional
    public void delete(Long id, String userId) {
        CourseMagazine toDelete = getById(id);

        // validate ownership
        if (!toDelete.getUser().getId().equals(userId)) {
            throw new NoPermissionOfCourseMagazineException();
        }

        // TODO: placeInCourseMagazine도 같이 제거되는 지 확인

        courseMagazineRepository.delete(id);
    }
}
