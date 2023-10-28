package kr.yeoksi.ours.oursserver.magazine.service.port.in;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;

import java.util.List;


public interface CourseMagazineService {

    public CourseMagazine publish(CourseMagazine courseMagazine);

    public CourseMagazine getById(Long id);

    public List<CourseMagazine> findLatestCourseMagazines(int count);

    public CourseMagazine update(CourseMagazine courseMagazine);

    public void delete(Long id);

}
