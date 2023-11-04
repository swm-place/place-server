package kr.yeoksi.ours.oursserver.course.adapter.in.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class CourseBookmarkListItemResponse {

    private Long id;
    private String title;
    private LocalDateTime createdAt;

}
