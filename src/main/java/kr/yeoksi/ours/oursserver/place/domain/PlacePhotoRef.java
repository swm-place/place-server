package kr.yeoksi.ours.oursserver.place.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
