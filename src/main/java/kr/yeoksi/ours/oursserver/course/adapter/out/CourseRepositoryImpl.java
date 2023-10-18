package kr.yeoksi.ours.oursserver.course.adapter.out;

import kr.yeoksi.ours.oursserver.course.adapter.out.entity.CourseJpaEntity;
import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.service.port.out.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CourseRepositoryImpl implements CourseRepository {

    private final CourseJpaRepository courseJPARepository;

    @Override
    public Course save(Course course) {
        CourseJpaEntity courseJpaEntity = CourseJpaEntity.from(course);
        return courseJPARepository.save(courseJpaEntity).toCourse();
    }
}
