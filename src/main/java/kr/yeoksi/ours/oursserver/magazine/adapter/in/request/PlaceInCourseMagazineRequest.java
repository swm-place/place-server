package kr.yeoksi.ours.oursserver.magazine.adapter.in.request;

import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PlaceInCourseMagazineRequest {

    private PlaceReference place;
    private String contents;

    private int order;


    public PlaceInCourseMagazine toPlaceInCourseMagazine() {
        return PlaceInCourseMagazine.builder()
                .place(place.toPlace())
                .contents(contents)
                .order(order)
                .build();
    }

}
