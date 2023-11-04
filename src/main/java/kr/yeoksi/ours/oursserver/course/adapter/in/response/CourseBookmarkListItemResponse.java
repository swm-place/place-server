package kr.yeoksi.ours.oursserver.course.adapter.in.response;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class CourseBookmarkListItemResponse {

    private Long id;
    private String title;
    private LocalDateTime createdAt;


    public static CourseBookmarkListItemResponse from(CourseBookmark courseBookmark) {
        return CourseBookmarkListItemResponse.builder()
                .id(courseBookmark.getId())
                .title(courseBookmark.getTitle())
                .createdAt(courseBookmark.getCreatedAt())
                .build();
    }
}
