package kr.yeoksi.ours.oursserver.place.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceOpeningHour {

    private Integer weekday;
    private Integer open;
    private Integer close;

}
