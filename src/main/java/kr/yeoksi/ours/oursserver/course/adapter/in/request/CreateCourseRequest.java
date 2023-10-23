package kr.yeoksi.ours.oursserver.course.adapter.in.request;


import jakarta.validation.constraints.NotNull;
import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class CreateCourseRequest {
    @NotNull private String title;
    private String description;
    private List<PlacesInCourseRequest> placesInCourse;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private boolean inProgress = false;
    private boolean isFinished = false;

    public Course toCourse() {
        List<PlaceInCourse> placesInCourse = new ArrayList<>(
                this.placesInCourse.stream()
                        .map(PlacesInCourseRequest::toPlaceInCourse)
                        .toList()
        );

        return Course.builder()
                .title(title)
                .description(description)
                .placesInCourse(placesInCourse)
                .startAt(startAt)
                .endAt(endAt)
                .build();
    }
}
