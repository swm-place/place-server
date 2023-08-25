package kr.yeoksi.ours.oursserver.domain.dto.place.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 장소 세부정보 조회의 응답을 위한 DTO
 */
@Data
@AllArgsConstructor
public class ReadPlaceResponse {

    private Long id;
    private String name;
    private String imgUrl;
    private List<String> hashtagList;
    private boolean isBookmark;
    private boolean isFavorite;
    private int favoriteCnt;
    private String summary;
    private String roadAddress;
    private String address;
    // 운영시간 추가 필요
    private boolean isOpen;
    private int openCnt;
    // 전화번호 추가 필요
    private List<ReadPlaceReviewResponse> placeReviewList;
    private List<String> placeImgUrlList;
    // 관련 장소
    // 관련 스토리
}
