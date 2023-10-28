package kr.yeoksi.ours.oursserver.magazine.service.port.in;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;

import java.util.List;
import java.util.Optional;


public interface CourseMagazineService {

    public CourseMagazine publish(CourseMagazine courseMagazine, String userId);

    public CourseMagazine getById(Long id);

    public List<CourseMagazine> findLatestCourseMagazines(int count, int page);

    public CourseMagazine update(CourseMagazine courseMagazine, String userId);

    public void delete(Long id, String userId);

}
