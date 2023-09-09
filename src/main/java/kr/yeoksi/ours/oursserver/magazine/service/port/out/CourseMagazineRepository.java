package kr.yeoksi.ours.oursserver.magazine.service.port.out;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;

import java.util.List;


public interface CourseMagazineRepository {

    public CourseMagazine persist(CourseMagazine courseMagazine);

    public CourseMagazine findById(Long id);

    public List<CourseMagazine> findLatestCourseMagazines(int count);

    public CourseMagazine update(CourseMagazine courseMagazine);

    public void delete(Long id);
}
