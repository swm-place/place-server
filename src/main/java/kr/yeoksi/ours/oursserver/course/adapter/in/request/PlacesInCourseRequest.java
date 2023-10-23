package kr.yeoksi.ours.oursserver.course.adapter.in.request;

import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import lombok.Data;


@Data
public class PlacesInCourseRequest {
    private Long id;

    public PlaceInCourse toPlaceInCourse() {
        return PlaceInCourse.builder()
                .id(id)
                .build();
    }
}
