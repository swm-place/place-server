package kr.yeoksi.ours.oursserver.common.domain;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Location {

    private Float latitude;
    private Float longitude;

}
