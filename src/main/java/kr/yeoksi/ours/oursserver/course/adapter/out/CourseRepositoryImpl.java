package kr.yeoksi.ours.oursserver.course.adapter.out;

import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity.CourseJpaEntity;
import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.CourseJpaRepository;
import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.PlaceInCourseJpaRepository;
import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CourseRepositoryImpl implements CourseRepository {

    private final CourseJpaRepository courseJPARepository;
    private final PlaceInCourseJpaRepository placeInCourseJpaRepository;

    @Override
    public Course save(Course course) {
        CourseJpaEntity courseJpaEntity = CourseJpaEntity.from(course);

        // 양방향 연관관계 모두 삭제 처리
        // TODO: 코드 재점검
//        courseJpaEntity.setPlacesInCourse(
//                courseJpaEntity.getPlacesInCourse().stream()
//                        .peek(placeInCourseJpaEntity -> placeInCourseJpaEntity.setCourse(courseJpaEntity))
//                        .peek(placeInCourseJpaEntity -> placeInCourseJpaEntity.setId(
//                                placeInCourseJpaRepository.save(placeInCourseJpaEntity).getId()
//                        ))
//                        .toList());

        return courseJPARepository.save(courseJpaEntity).toCourse();
    }

    @Override
    public Optional<Course> findById(Long id) {
        Optional<CourseJpaEntity> courseJpaEntity = courseJPARepository.findById(id);
        return courseJpaEntity.map(CourseJpaEntity::toCourse);
    }

    @Override
    public List<Course> findAllByUserId(String userId) {
        List<CourseJpaEntity> courseJpaEntities = courseJPARepository.findAllByUserId(userId);
        return courseJpaEntities.stream()
                .map(CourseJpaEntity::toCourse)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        // 양방향 연관관계 모두 삭제 처리
        // TODO: 코드 재점검
//        placeInCourseJpaRepository.deleteAll(placeInCourseJpaRepository.findAllByCourseId(id));

        courseJPARepository.deleteById(id);
    }
}
