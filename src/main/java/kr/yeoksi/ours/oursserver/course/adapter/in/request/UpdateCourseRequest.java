package kr.yeoksi.ours.oursserver.course.adapter.in.request;

import jakarta.validation.constraints.NotNull;
import kr.yeoksi.ours.oursserver.course.adapter.in.reference.PlaceInCourseReference;
import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateCourseRequest extends CreateCourseRequest {

    @NotNull private Long id;

    @Override
    public Course toCourse() {
        List<PlaceInCourse> placesInCourse = new ArrayList<>(
                this.getPlacesInCourse().stream()
                        .map(PlaceInCourseReference::toPlaceInCourse)
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
