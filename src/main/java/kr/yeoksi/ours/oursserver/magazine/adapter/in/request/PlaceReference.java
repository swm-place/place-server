package kr.yeoksi.ours.oursserver.magazine.adapter.in.request;

import kr.yeoksi.ours.oursserver.others.domain.Place;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PlaceReference {

    private String id;


    public Place toPlace() {
        return Place.builder()
                .id(id)
                .build();
    }

}
