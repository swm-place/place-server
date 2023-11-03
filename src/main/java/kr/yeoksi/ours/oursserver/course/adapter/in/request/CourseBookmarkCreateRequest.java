package kr.yeoksi.ours.oursserver.course.adapter.in.request;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CourseBookmarkCreateRequest {

    private Long id;
    private String title;

}
