package kr.yeoksi.ours.oursserver.course.adapter.in.request;


import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class CreateCourseRequest {
    private String title;
    private String description;
    private List<PlaceInCourseCreateRequest> placesInCourse;
    private String routesJson;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private boolean inProgress = false;
    private boolean isFinished = false;

    public Course toCourse() {
        List<PlaceInCourse> placesInCourse = new ArrayList<>(
                this.placesInCourse.stream()
                        .map(PlaceInCourseCreateRequest::toPlaceInCourse)
                        .toList()
        );

        return Course.builder()
                .title(title)
                .description(description)
                .placesInCourse(placesInCourse)
                .routesJson(routesJson)
                .startAt(startAt)
                .endAt(endAt)
                .build();
    }
}
