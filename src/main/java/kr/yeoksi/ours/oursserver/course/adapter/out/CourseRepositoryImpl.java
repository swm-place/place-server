package kr.yeoksi.ours.oursserver.course.adapter.out;

import kr.yeoksi.ours.oursserver.course.adapter.out.entity.CourseJpaEntity;
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

    @Override
    public Course save(Course course) {
        CourseJpaEntity courseJpaEntity = CourseJpaEntity.from(course);
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
        courseJPARepository.deleteById(id);
    }
}
