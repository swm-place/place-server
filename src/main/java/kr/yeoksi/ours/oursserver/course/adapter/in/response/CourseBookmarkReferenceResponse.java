package kr.yeoksi.ours.oursserver.course.adapter.in.response;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CourseBookmarkReferenceResponse {

    private Long id;
    private String title;


    public static CourseBookmarkReferenceResponse from(CourseBookmark courseBookmark) {
        return CourseBookmarkReferenceResponse.builder()
                .id(courseBookmark.getId())
                .title(courseBookmark.getTitle())
                .build();
    }

}
