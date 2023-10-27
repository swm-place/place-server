package kr.yeoksi.ours.oursserver.course.adapter.out;

import kr.yeoksi.ours.oursserver.course.adapter.out.entity.CourseJpaEntity;
import kr.yeoksi.ours.oursserver.course.adapter.out.entity.PlaceInCourseJpaEntity;
import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.course.service.port.out.PlaceInCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class PlaceInCourseRepositoryImpl implements PlaceInCourseRepository {

    private final PlaceInCourseJpaRepository placeInCourseJpaRepository;


    @Override
    public PlaceInCourse save(PlaceInCourse placeInCourse, Course course) {
        return placeInCourseJpaRepository.save(
                PlaceInCourseJpaEntity.from(
                        placeInCourse,
                        CourseJpaEntity.from(course)))
                .toPlaceInCourse();
    }

    @Override
    public Optional<PlaceInCourse> findById(Long id) {
        return placeInCourseJpaRepository.findById(id)
                .map(PlaceInCourseJpaEntity::toPlaceInCourse);
    }

    @Override
    public List<PlaceInCourse> findAllByCourseId(Long courseId) {
        return placeInCourseJpaRepository.findAllByCourseId(courseId)
                .stream()
                .map(PlaceInCourseJpaEntity::toPlaceInCourse)
                .toList();
    }

    @Override
    public void deleteById(Long id) {

    }
}
