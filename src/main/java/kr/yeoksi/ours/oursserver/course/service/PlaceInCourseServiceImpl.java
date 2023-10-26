package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.course.exception.NotExistedCourseException;
import kr.yeoksi.ours.oursserver.course.exception.PlaceWrongReferenceWithCourseException;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseService;
import kr.yeoksi.ours.oursserver.course.service.port.in.PlaceInCourseService;
import kr.yeoksi.ours.oursserver.course.service.port.out.PlaceInCourseRepository;
import kr.yeoksi.ours.oursserver.others.exception.NotExistedPlaceException;
import kr.yeoksi.ours.oursserver.others.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PlaceInCourseServiceImpl implements PlaceInCourseService {

    private final PlaceInCourseRepository placeInCourseRepository;

    private final CourseService courseService;
    private final PlaceService placeService;


    @Override
    public PlaceInCourse append(PlaceInCourse placeInCourse, String userId) {
        // validate right course and owner
        Course course = courseService.findById(placeInCourse.getCourseId(), userId)
                .orElseThrow(NotExistedCourseException::new);

        // validate right place and sync
        try {
            placeInCourse.setPlace(
                    placeService.findById(placeInCourse.getPlace().getId()));
        } catch (NotExistedPlaceException e) {
            // TODO: Place 관련 예외 처리 ServiceException으로 통합
            throw new PlaceWrongReferenceWithCourseException();
        }

        placeInCourse.setId(null);
        return placeInCourseRepository.save(placeInCourse);
    }

    @Override
    public PlaceInCourse update(PlaceInCourse placeInCourse, String userId) {
        return null;
    }

    @Override
    public void delete(Long id, String userId) {

    }

    @Override
    public Optional<PlaceInCourse> findById(Long id, String userId) {
        return null;
    }

    @Override
    public List<PlaceInCourse> findByCourseId(Long courseId, String userId) {
        return null;
    }
}
