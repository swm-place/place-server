package kr.yeoksi.ours.oursserver.domain.dto.place.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReadPlaceFromElastic {

    private String name;
    private List<String> hashtagList;
    private String summary;
    private String roadAddress;
    private String address;
    // 운영시간 추가 필요
    // 전화번호 추가 필요
}
