package kr.yeoksi.ours.oursserver.course.adapter.in.request;

import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class PlaceInCourseUpdateRequest {
    private Long id;

    private PlaceReference place;

    private int day;
    private int order;

    private LocalDateTime startAt;
    private int timeRequired;
    private int transportationTime;

    private LocalDateTime createdAt;

    public PlaceInCourse toPlaceInCourse() {
        return PlaceInCourse.builder()
                .place(place.toPlace())
                .day(day)
                .order(order)
                .startAt(startAt)
                .timeRequired(timeRequired)
                .transportationTime(transportationTime)
                .build();
    }
}
