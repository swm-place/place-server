package kr.yeoksi.ours.oursserver.course.adapter.out;

import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.course.service.port.out.PlaceInCourseRepository;

import java.util.List;
import java.util.Optional;

public class PlaceInCourseRepositoryImpl implements PlaceInCourseRepository {
    @Override
    public PlaceInCourse save(PlaceInCourse placeInCourse) {
        return null;
    }

    @Override
    public Optional<PlaceInCourse> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PlaceInCourse> findAllByCourseId(Long courseId) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
