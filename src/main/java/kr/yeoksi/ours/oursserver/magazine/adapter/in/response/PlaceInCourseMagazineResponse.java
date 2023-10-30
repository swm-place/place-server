package kr.yeoksi.ours.oursserver.magazine.adapter.in.response;

import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PlaceInCourseMagazineResponse {

    private Long id;

    private PlaceWithCourseMagazineResponse place;
    private String contents;

    private int order;


    public static PlaceInCourseMagazineResponse from(PlaceInCourseMagazine placeInCourseMagazine) {
        return PlaceInCourseMagazineResponse.builder()
                .id(placeInCourseMagazine.getId())
                .place(PlaceWithCourseMagazineResponse.from(placeInCourseMagazine.getPlace()))
                .contents(placeInCourseMagazine.getContents())
                .order(placeInCourseMagazine.getOrder())
                .build();
    }
}
