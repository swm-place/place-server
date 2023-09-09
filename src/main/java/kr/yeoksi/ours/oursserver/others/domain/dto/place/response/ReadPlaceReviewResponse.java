package kr.yeoksi.ours.oursserver.others.domain.dto.place.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 장소 상세 조회 페이지에서 한줄평을 전달하기 위한 DTO
 */
@Data
@AllArgsConstructor
public class ReadPlaceReviewResponse {

    private Long placeReviewId;
    private String contents;
    private String userImgUrl;
    private String userName;
    private LocalDateTime createdAt;
    private boolean isFavorite;
    private boolean isComplain;
}