package kr.yeoksi.ours.oursserver.course.adapter.in.response;

import kr.yeoksi.ours.oursserver.course.adapter.in.request.PlaceReference;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class PlaceInCourseResponse {
    private Long id;

    private PlaceWithCourseResponse place;

    private int day;
    private int order;

    private LocalDateTime startAt;
    private int timeRequired;
    private int transportationTime;

    private LocalDateTime createdAt;


    public static PlaceInCourseResponse from(PlaceInCourse placeInCourse) {
        return PlaceInCourseResponse.builder()
                .id(placeInCourse.getId())
                .place(PlaceWithCourseResponse.from(placeInCourse.getPlace()))
                .day(placeInCourse.getDay())
                .order(placeInCourse.getOrder())
                .startAt(placeInCourse.getStartAt())
                .timeRequired(placeInCourse.getTimeRequired())
                .transportationTime(placeInCourse.getTransportationTime())
                .createdAt(placeInCourse.getCreatedAt())
                .build();
    }

    public static PlaceInCourseResponse from(PlaceInCourse placeInCourse, String imgRequestBaseUrl) {
        PlaceInCourseResponse placeInCourseResponse = from(placeInCourse);

        placeInCourseResponse.getPlace().setImgUrl(imgRequestBaseUrl + "/" + placeInCourse.getPlace().getId());
        return placeInCourseResponse;
    }


}
