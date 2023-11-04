package kr.yeoksi.ours.oursserver.course.adapter.in.response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CourseBookmarkReferenceResponse {

    private Long id;
    private String title;

}
