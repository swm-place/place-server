package kr.yeoksi.ours.oursserver.course.adapter.in.response;

import kr.yeoksi.ours.oursserver.course.adapter.in.request.PlaceInCourseReference;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class CourseResponse {
    private Long id;

    private String title;
    private String description;

    private List<PlaceInCourseReference> placesInCourse = new ArrayList<>();

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private boolean inProgress;
    private boolean isFinished;

    private LocalDateTime createdAt;
}
