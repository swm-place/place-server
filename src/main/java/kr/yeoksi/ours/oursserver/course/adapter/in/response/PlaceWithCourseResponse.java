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
    // TODO: 운영시간 반영 (구글 연동 + DB)
//    private Map<String, Object> openTime;


    public static PlaceWithCourseResponse from(Place place) {
        return PlaceWithCourseResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .category(place.getCategory())
//                .openTime(place.getOpenTime())
                .build();
    }
}
