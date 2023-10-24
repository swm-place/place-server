package kr.yeoksi.ours.oursserver.course.adapter.in.response;

import kr.yeoksi.ours.oursserver.course.adapter.in.request.PlaceInCourseRequest;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class CourseResponse {
    private Long id;

    private String title;
    private String description;

    private List<PlaceInCourse> placesInCourse = new ArrayList<>();

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private boolean inProgress;
    private boolean isFinished;

    private LocalDateTime createdAt;
}
