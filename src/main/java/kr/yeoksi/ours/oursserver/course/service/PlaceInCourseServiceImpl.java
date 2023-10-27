package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.course.exception.NotExistedCourseException;
import kr.yeoksi.ours.oursserver.course.exception.NotExistedPlaceInCourseException;
import kr.yeoksi.ours.oursserver.course.exception.PlaceWrongReferenceWithCourseException;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseService;
import kr.yeoksi.ours.oursserver.course.service.port.in.PlaceInCourseService;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseRepository;
import kr.yeoksi.ours.oursserver.course.service.port.out.PlaceInCourseRepository;
import kr.yeoksi.ours.oursserver.others.exception.NotExistedPlaceException;
import kr.yeoksi.ours.oursserver.others.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PlaceInCourseServiceImpl implements PlaceInCourseService {

    private final PlaceInCourseRepository placeInCourseRepository;

    private final CourseService courseService;
    private final PlaceService placeService;


    @Override
    @Transactional
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
        return placeInCourseRepository.save(placeInCourse, course);
    }

    @Override
    @Transactional
    public PlaceInCourse update(PlaceInCourse placeInCourse, String userId) {
        // validate right course and owner
        Course course = courseService.findById(placeInCourse.getCourseId(), userId)
                .orElseThrow(NotExistedCourseException::new);

        // validate existed placeInCourse
        PlaceInCourse placeInCourseToUpdate = placeInCourseRepository.findById(placeInCourse.getId())
                .orElseThrow(NotExistedPlaceInCourseException::new);

        // validate right place and sync
        try {
            placeInCourseToUpdate.setPlace(
                    placeService.findById(placeInCourse.getPlace().getId()));
        } catch (NotExistedPlaceException e) {
            throw new PlaceWrongReferenceWithCourseException();
        }

        placeInCourseToUpdate.update(placeInCourse);
        return placeInCourseRepository.save(placeInCourseToUpdate, course);
    }

    @Override
    @Transactional
    public void delete(Long id, Long courseId, String userId) {
        // validate existed placeInCourse
        PlaceInCourse placeInCourseToDelete = placeInCourseRepository.findById(id)
                .orElseThrow(NotExistedPlaceInCourseException::new);

        // validate right course and owner
        Course course = courseService.findById(placeInCourseToDelete.getCourseId(), userId)
                .filter(c -> c.getId().equals(courseId))
                .orElseThrow(NotExistedCourseException::new);

        placeInCourseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PlaceInCourse getById(Long id, Long courseId, String userId) {
        // validate existed placeInCourse
        PlaceInCourse placeInCourse = placeInCourseRepository.findById(id)
                .orElseThrow(NotExistedPlaceInCourseException::new);

        // validate right course and owner
        Course course = courseService.findById(placeInCourse.getCourseId(), userId)
                .filter(c -> c.getId().equals(courseId))
                .orElseThrow(NotExistedCourseException::new);

        return placeInCourse;
    }

    @Override
    @Transactional
    public List<PlaceInCourse> findByCourseId(Long courseId, String userId) {
        // validate right course and owner
        Course course = courseService.findById(courseId, userId)
                .orElseThrow(NotExistedCourseException::new);

        return placeInCourseRepository.findAllByCourseId(courseId);
    }
}
