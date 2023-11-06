package kr.yeoksi.ours.oursserver.others.domain.dto.place.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ThumbnailInfoResponse {

    private String placeName;
    private String placeImgUrl;

    public ThumbnailInfoResponse(String placeName, String placeImgUrl) {
        this.placeName = placeName;
        this.placeImgUrl = placeImgUrl;
    }
}
