package kr.yeoksi.ours.oursserver.place_full.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
public class PlacePhotoRef {

    private Long id;
    private String placeId;
    private String url;
    private LocalDateTime createdAt;
    private Integer width;
    private Integer height;

    private List<GooglePlacePhotoAttribution> googleAttributions;
    private String googleRefId;

}
