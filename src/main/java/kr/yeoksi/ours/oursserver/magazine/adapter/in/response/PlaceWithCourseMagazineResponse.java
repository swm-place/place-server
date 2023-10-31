package kr.yeoksi.ours.oursserver.magazine.adapter.in.response;

import kr.yeoksi.ours.oursserver.others.domain.Place;
import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Data
@Builder
public class PlaceWithCourseMagazineResponse {

    private String id;

    private String name;
    private String category;

    // TODO: 운영시간 반영 (구글 연동 + DB)
//    private Map<String, Object> openTime;


    public static PlaceWithCourseMagazineResponse from(Place place) {
        return PlaceWithCourseMagazineResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .category(place.getCategory())
//                .openTime(place.getOpenTime())
                .build();
    }

}
