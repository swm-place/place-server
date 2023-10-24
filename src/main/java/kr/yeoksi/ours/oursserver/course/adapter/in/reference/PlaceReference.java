package kr.yeoksi.ours.oursserver.course.adapter.in.reference;


import jakarta.validation.constraints.NotNull;
import kr.yeoksi.ours.oursserver.others.domain.Place;
import lombok.Data;


@Data
public class PlaceReference {

    @NotNull private String id;

    public Place toPlace() {
        return Place.builder()
                .id(id)
                .build();
    }
}
