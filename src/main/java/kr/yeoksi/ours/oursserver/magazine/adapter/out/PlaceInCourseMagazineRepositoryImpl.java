package kr.yeoksi.ours.oursserver.magazine.adapter.out;

import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.PlaceInCourseMagazineRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PlaceInCourseMagazineRepositoryImpl implements PlaceInCourseMagazineRepository {
    @Override
    public PlaceInCourseMagazine save(PlaceInCourseMagazine placeInCourseMagazine) {
        return null;
    }

    @Override
    public PlaceInCourseMagazine findById(Long id) {
        return null;
    }

    @Override
    public List<PlaceInCourseMagazine> findByMagazineId(Long magazineId) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
