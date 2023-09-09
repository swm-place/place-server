package kr.yeoksi.ours.oursserver.magazine.service;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineService;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.CourseMagazineRepository;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.MagazineImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseMagazineServiceImpl implements CourseMagazineService {

    private final CourseMagazineRepository courseMagazineRepository;
    private final MagazineImageRepository magazineImageRepository;

    @Override
    public CourseMagazine publish(CourseMagazine courseMagazine) {
        return null;
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
