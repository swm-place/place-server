package kr.yeoksi.ours.oursserver.domain.idclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlacesInGroupId implements Serializable {

    private Long place;
    private Long placeGroup;
}
