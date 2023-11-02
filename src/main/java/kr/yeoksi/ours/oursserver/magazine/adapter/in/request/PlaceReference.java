package kr.yeoksi.ours.oursserver.magazine.adapter.in.request;

import kr.yeoksi.ours.oursserver.others.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceReference {

    private String id;


    public Place toPlace() {
        return Place.builder()
                .id(id)
                .build();
    }

}
