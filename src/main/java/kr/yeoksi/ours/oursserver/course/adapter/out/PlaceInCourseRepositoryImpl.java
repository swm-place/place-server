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
    private final CourseJpaRepository courseJpaRepository;


    @Override
    public PlaceInCourse save(PlaceInCourse placeInCourse, Course course) {
        // 양방향 연관관계 모두 저장 처리
        // TODO: 코드 재점검
        CourseJpaEntity courseToSave = courseJpaRepository.findById(course.getId())
                .orElse(courseJpaRepository.save(CourseJpaEntity.from(course)));

        courseToSave.setPlacesInCourse(
                courseToSave.getPlacesInCourse().stream()
                        .peek(placeInCourseJpaEntity -> placeInCourseJpaEntity.setCourse(courseToSave))
                        .toList());
        courseJpaRepository.save(courseToSave);

        return placeInCourseJpaRepository.save(
                PlaceInCourseJpaEntity.from(
                        placeInCourse,
                        courseToSave))
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
        placeInCourseJpaRepository.deleteById(id);
    }
}
