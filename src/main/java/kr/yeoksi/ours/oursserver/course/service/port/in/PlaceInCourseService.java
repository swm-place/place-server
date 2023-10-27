package kr.yeoksi.ours.oursserver.course.service.port.in;

import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;

import java.util.List;
import java.util.Optional;

public interface PlaceInCourseService {

    PlaceInCourse append(PlaceInCourse placeInCourse, String userId);

    PlaceInCourse update(PlaceInCourse placeInCourse, String userId);

    void delete(Long id, Long courseId, String userId);

    PlaceInCourse getById(Long id, Long courseId, String userId);

    List<PlaceInCourse> findByCourseId(Long courseId, String userId);

}
