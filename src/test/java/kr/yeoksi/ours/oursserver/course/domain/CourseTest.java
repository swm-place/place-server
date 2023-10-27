package kr.yeoksi.ours.oursserver.course.domain;


import kr.yeoksi.ours.oursserver.course.domain.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CourseTest {

    @Test
    public void update를_통해_null이_아닌_일부_필드만을_업데이트할_수_있다() {
        // given
        Course course = Course.builder()
                .title("title origin")
                .description("description origin")
                .build();

        Course course2 = Course.builder()
                .description("description new")
                .isFinished(true)
                .build();

        // when
        course.update(course2);

        // then
        assertThat(course.getTitle()).isEqualTo("title origin");
        assertThat(course.getDescription()).isEqualTo("description new");
        assertThat(course.isFinished()).isEqualTo(true);

    }
}
