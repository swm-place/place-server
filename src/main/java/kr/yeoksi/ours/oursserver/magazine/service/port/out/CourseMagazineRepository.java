package kr.yeoksi.ours.oursserver.magazine.service.port.out;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;

import java.util.List;
import java.util.Optional;


public interface CourseMagazineRepository {

    public CourseMagazine save(CourseMagazine courseMagazine);

    public Optional<CourseMagazine> findById(Long id);

    public List<CourseMagazine> findLatestCourseMagazines(int count);

    public void delete(Long id);
}
