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
    private String imgUrl;


    public static PlaceWithCourseMagazineResponse from(Place place) {
        return PlaceWithCourseMagazineResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .category(place.getCategory())
                .imgUrl(place.getImgUrl())
                .build();
    }

}
