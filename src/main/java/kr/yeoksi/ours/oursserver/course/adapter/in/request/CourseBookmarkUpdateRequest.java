package kr.yeoksi.ours.oursserver.course.adapter.in.request;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseBookmarkUpdateRequest {

    private Long id;
    private String title;


    public CourseBookmark toCourseBookmark() {
        return CourseBookmark.builder()
                .id(id)
                .title(title)
                .build();
    }

}
