package kr.yeoksi.ours.oursserver.magazine.domain;

import kr.yeoksi.ours.oursserver.others.domain.Place;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PlaceInCourseMagazine {

    private Long id;
    private CourseMagazine courseMagazine;

    private Place place;

    private String contents;
    private int order;

}
