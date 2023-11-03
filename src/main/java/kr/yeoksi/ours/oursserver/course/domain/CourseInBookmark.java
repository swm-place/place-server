package kr.yeoksi.ours.oursserver.course.domain;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CourseInBookmark {

    private Long id;
    private CourseBookmark courseBookmark;
    private Course course;
    private String createdAt;

}
