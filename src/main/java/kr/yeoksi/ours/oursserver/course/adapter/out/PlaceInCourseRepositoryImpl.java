package kr.yeoksi.ours.oursserver.course.adapter.out;

import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity.CourseJpaEntity;
import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity.PlaceInCourseJpaEntity;
import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.CourseJpaRepository;
import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.PlaceInCourseJpaRepository;
import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.course.service.port.out.PlaceInCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

        placeInCourse = placeInCourseJpaRepository.save(PlaceInCourseJpaEntity.from(placeInCourse, courseToSave))
                .toPlaceInCourse();

        List<PlaceInCourseJpaEntity> placesInCourse = new ArrayList<>(courseToSave.getPlacesInCourse());

        PlaceInCourse finalPlaceInCourse = placeInCourse;
        if (placesInCourse.stream().noneMatch(placeInCourseJpaEntity -> placeInCourseJpaEntity.getId().equals(finalPlaceInCourse.getId()))) {
            placesInCourse.add(PlaceInCourseJpaEntity.from(placeInCourse, courseToSave));
        }

        courseToSave.setPlacesInCourse(placesInCourse);
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
        return placeInCourseJpaRepository.findAllByCourseIdOrderByVisitOrderDesc(courseId)
                .stream()
                .map(PlaceInCourseJpaEntity::toPlaceInCourse)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        // 양방향 연관관계 모두 삭제 처리
        // TODO: 코드 재점검
        Optional<PlaceInCourseJpaEntity> placeInCourse = placeInCourseJpaRepository.findById(id);
        if (placeInCourse.isEmpty()) return;

        CourseJpaEntity courseToSave = courseJpaRepository.findById(placeInCourse.get().getCourse().getId())
                .orElse(courseJpaRepository.save(placeInCourse.get().getCourse()));

        List<PlaceInCourseJpaEntity> placesInCourse = new ArrayList<>(courseToSave.getPlacesInCourse());
        placesInCourse.remove(placesInCourse.stream()
                .filter(placeInCourseJpaEntity -> placeInCourseJpaEntity.getId().equals(id))
                .findFirst()
                .orElseThrow());

        courseToSave.setPlacesInCourse(placesInCourse);
        courseJpaRepository.save(courseToSave);

        placeInCourseJpaRepository.deleteById(id);
    }
}
