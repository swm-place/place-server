package kr.yeoksi.ours.oursserver.course.service.port.in;

import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;

import java.util.List;
import java.util.Optional;

public interface PlaceInCourseService {

    PlaceInCourse append(PlaceInCourse placeInCourse);

    PlaceInCourse update(PlaceInCourse placeInCourse);

    void delete(PlaceInCourse placeInCourse);

    PlaceInCourse findById(Long id);

    List<PlaceInCourse> findByCourseId(Long courseId);

}
