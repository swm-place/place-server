package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.course.service.port.in.PlaceInCourseService;

import java.util.List;

public class PlaceInCourseServiceImpl implements PlaceInCourseService {
    @Override
    public PlaceInCourse append(PlaceInCourse placeInCourse, String userId) {
        // validate right course and owner


        // validate right place
        return null;
    }

    @Override
    public PlaceInCourse update(PlaceInCourse placeInCourse, String userId) {
        return null;
    }

    @Override
    public void delete(Long id, String userId) {

    }

    @Override
    public PlaceInCourse findById(Long id, String userId) {
        return null;
    }

    @Override
    public List<PlaceInCourse> findByCourseId(Long courseId, String userId) {
        return null;
    }
}
