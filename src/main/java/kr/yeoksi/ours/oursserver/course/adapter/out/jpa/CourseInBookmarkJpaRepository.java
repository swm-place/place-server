package kr.yeoksi.ours.oursserver.course.adapter.out.jpa;

import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity.CourseInBookmarkJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseInBookmarkJpaRepository extends JpaRepository<CourseInBookmarkJpaEntity, Long> {

    List<CourseInBookmarkJpaEntity> findByCourseBookmarkIdAndCourseId(Long courseBookmarkId, Long courseId);

    boolean existsByCourseBookmarkIdAndCourseId(Long courseBookmarkId, Long courseId);

}
