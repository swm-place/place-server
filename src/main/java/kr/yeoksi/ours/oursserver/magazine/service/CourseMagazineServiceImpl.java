package kr.yeoksi.ours.oursserver.magazine.service;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.exception.DuplicatedCourseMagazineException;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineService;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.CourseMagazineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseMagazineServiceImpl implements CourseMagazineService {

    private CourseMagazineRepository courseMagazineRepository;


    @Override
    public CourseMagazine publish(CourseMagazine courseMagazine) {
        // validate duplicated
        Optional<CourseMagazine> found = courseMagazineRepository.findById(courseMagazine.getId());
        if (found.isPresent()) {
            throw new DuplicatedCourseMagazineException();
        }

        // place, user 연동

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
