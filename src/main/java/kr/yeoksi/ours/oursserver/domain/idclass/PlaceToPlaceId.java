package kr.yeoksi.ours.oursserver.domain.idclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceToPlaceId implements Serializable {

    private Long leftPlace;
    private Long rightPlace;
}
