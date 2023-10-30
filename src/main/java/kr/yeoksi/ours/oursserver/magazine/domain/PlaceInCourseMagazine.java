package kr.yeoksi.ours.oursserver.magazine.domain;

import kr.yeoksi.ours.oursserver.others.domain.Place;
import kr.yeoksi.ours.oursserver.utils.EntityUtils;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PlaceInCourseMagazine {

    private Long id;

    private Place place;

    private String contents;
    private int order;


    public void update(PlaceInCourseMagazine source) {
        EntityUtils.updateNotNullProperties(this, source);
    }

}
