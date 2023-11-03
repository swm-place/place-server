package kr.yeoksi.ours.oursserver.course.adapter.in.response;

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
    private List<CourseInBookmarkResponse> coursesInBookmark;

}
