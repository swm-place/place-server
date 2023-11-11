package kr.yeoksi.ours.oursserver.place_full.domain;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class GooglePlacePhotoAttribution {

    private String htmlAttribution;
    private String url;
    private String author;

}
