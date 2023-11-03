package kr.yeoksi.ours.oursserver.course.adapter.in.response;

import java.time.LocalDateTime;
import java.util.List;

public class CourseBookmarkResponse {

    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private List<CourseInBookmarkResponse> coursesInBookmark;

}
