package kr.yeoksi.ours.oursserver.course.adapter.in.response;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import kr.yeoksi.ours.oursserver.course.domain.CourseInBookmark;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
public class CourseBookmarkResponse {

    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private List<CourseResponse> courses;


    public static CourseBookmarkResponse from(CourseBookmark courseBookmark) {
        return CourseBookmarkResponse.builder()
                .id(courseBookmark.getId())
                .title(courseBookmark.getTitle())
                .createdAt(courseBookmark.getCreatedAt())
                .courses(courseBookmark.getCoursesInBookmark().stream()
                        .map(CourseInBookmark::getCourse)
                        .map(CourseResponse::from)
                        .toList())
                .build();
    }

}
