package kr.yeoksi.ours.oursserver.course.adapter.in.request;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;

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
