package kr.yeoksi.ours.oursserver.magazine.adapter.in.request;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PlaceInCourseMagazineCreateRequest {

    private PlaceReference place;
    private String contents;

    private int order;

}
