package kr.yeoksi.ours.oursserver.course.adapter.in.response;

import kr.yeoksi.ours.oursserver.others.domain.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
@Builder
public class PlaceWithCourseResponse {

    private String id;

    private String name;
    private String category;
    private String imgUrl;


    public static PlaceWithCourseResponse from(Place place) {
        return PlaceWithCourseResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .category(place.getCategory())
                .build();
    }

    public static PlaceWithCourseResponse from(Place place, String imgRequestBaseUrl) {
        return PlaceWithCourseResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .category(place.getCategory())
                .imgUrl(imgRequestBaseUrl + "/" + place.getId())
                .build();
    }
}
