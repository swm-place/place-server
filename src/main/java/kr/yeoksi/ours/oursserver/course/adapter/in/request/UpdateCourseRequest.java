package kr.yeoksi.ours.oursserver.course.adapter.in.request;

import jakarta.validation.constraints.NotNull;
import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class UpdateCourseRequest {

    @NotNull private Long id;
    private String title;
    private String description;
    private List<PlaceInCourseUpdateRequest> placesInCourse;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private boolean inProgress = false;
    private boolean isFinished = false;


    public Course toCourse() {
        List<PlaceInCourse> placesInCourse = new ArrayList<>(
                this.getPlacesInCourse().stream()
                        .map(PlaceInCourseUpdateRequest::toPlaceInCourse)
                        .toList()
        );

        return Course.builder()
                .id(id)
                .title(this.getTitle())
                .description(this.getDescription())
                .placesInCourse(placesInCourse)
                .startAt(getStartAt())
                .endAt(getEndAt())
                .build();
    }
}
