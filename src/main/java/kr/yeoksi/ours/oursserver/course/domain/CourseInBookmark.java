package kr.yeoksi.ours.oursserver.course.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseInBookmark {

    private Long id;
    private CourseBookmark courseBookmark;
    private Course course;
    private LocalDateTime createdAt;

}
