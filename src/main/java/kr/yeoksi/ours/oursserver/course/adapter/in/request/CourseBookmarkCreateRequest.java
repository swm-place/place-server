package kr.yeoksi.ours.oursserver.course.adapter.in.request;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CourseBookmarkCreateRequest {

    private String title;


    public CourseBookmark toCourseBookmark() {
        return CourseBookmark.builder()
                .title(title)
                .build();
    }

}
