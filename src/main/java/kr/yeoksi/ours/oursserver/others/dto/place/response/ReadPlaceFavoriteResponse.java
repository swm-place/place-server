package kr.yeoksi.ours.oursserver.others.dto.place.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadPlaceFavoriteResponse {

    private String id;

    private String name;

    private String category;

    private String imgUrl;
}
