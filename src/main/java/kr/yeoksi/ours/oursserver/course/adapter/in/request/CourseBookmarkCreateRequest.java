package kr.yeoksi.ours.oursserver.course.adapter.in.request;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseBookmarkCreateRequest {

    private String title;


    public CourseBookmark toCourseBookmark() {
        return CourseBookmark.builder()
                .title(title)
                .build();
    }

}
