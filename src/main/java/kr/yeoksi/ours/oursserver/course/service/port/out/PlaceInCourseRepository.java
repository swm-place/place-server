package kr.yeoksi.ours.oursserver.course.service.port.out;

import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;

import java.util.List;
import java.util.Optional;

public interface PlaceInCourseRepository {

    PlaceInCourse save(PlaceInCourse placeInCourse);

    Optional<PlaceInCourse> findById(Long id);

    List<PlaceInCourse> findAllByCourseId(Long courseId);

    void deleteById(Long id);
}
