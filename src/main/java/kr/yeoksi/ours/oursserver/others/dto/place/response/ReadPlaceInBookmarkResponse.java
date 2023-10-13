package kr.yeoksi.ours.oursserver.others.dto.place.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReadPlaceInBookmarkResponse {

    private String placeId;
    private String elasticId;
    private String name;
    private String category;
    private String imgUrl;
    private List<String> hashtagList;
    private boolean isOpen;
}
