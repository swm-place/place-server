package kr.yeoksi.ours.oursserver.controller;

import kr.yeoksi.ours.oursserver.domain.Place;
import kr.yeoksi.ours.oursserver.domain.PlaceReview;
import kr.yeoksi.ours.oursserver.domain.Response;
import kr.yeoksi.ours.oursserver.service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaceApiController {

    private final PlaceService placeService;

    /**
     * id로 공간 하나 조회하기.
     */
    @GetMapping("/place/{placeIndex}")
    public ResponseEntity<Response<PlaceResponse>> readPlace(
            @RequestHeader("X-User-Uid") String uid,
            @PathVariable("placeIndex") Long id) {

        // 공간 정보 조회
        Place place = placeService.findById(id);

        // 공간에 매핑된 이미지 조회
        List<String> placeImgUrlList = placeService.getImgUrlList(id);

        // 공간에 매핑된 해시태그 조회
        List<String> hashtagList = placeService.getHashtagList(id);

        // 북마크 여부 확인
        boolean isBookmark = placeService.checkBookmark(uid, id);

        // 해당 공간의 좋아요 개수 확인
        int favorites = placeService.getFavoriteCount(id);

        // 유저의 좋아요 여부 확인
        boolean isFavorite = placeService.checkFavorite(uid, id);

        // 현재 운영중이라고 응답한 유저의 수 조회
        int open = placeService.getOpenCount(id);

        // 유저의 운영중 응답 여부 확인
        boolean isOpen = placeService.checkOpen(uid, id);

        // 공간에 매핑된 한줄평들 조회하기
        List<PlaceReview> placeReviewList = placeService.getAllPlaceReviewList(id);
        List<PlaceReviewResponse> placeReviewResponseList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(placeReviewList)) {
            for(PlaceReview placeReview : placeReviewList) {
                placeReviewResponseList.add(
                        new PlaceReviewResponse(
                                placeReview.getId(),
                                placeReview.getUser().getId(),
                                placeReview.getUser().getNickname(),
                                placeReview.getUser().getImgUrl(),
                                placeReview.getContents(),
                                placeReview.getCratedAt(),
                                placeService.checkReviewFavorite(
                                        placeReview.getUser().getId(),
                                        placeReview.getId())));
            }
        }

        return ResponseEntity.ok().body(
                Response.success(
                        new PlaceResponse(
                                place.getName(),
                                place.getCategory(),
                                place.getPhoneNumber(),
                                place.getAddress(),
                                place.getLongitude(),
                                place.getLatitude(),
                                place.getLocationCode(),
                                place.getActivity(),
                                place.getCreatedAt(),
                                isBookmark,
                                favorites,
                                isFavorite,
                                open,
                                isOpen,
                                hashtagList,
                                placeImgUrlList,
                                placeReviewResponseList
                        )));
    }

    /**
     * id로 공간 조회하는 것에 응답을 위한 DTO
     */
    @Data
    @AllArgsConstructor
    static class PlaceResponse {

        private String name;
        private String category;
        private String phoneNumber;
        private String address;
        private Double longitude;
        private Double latitude;
        private Integer locationCode;
        private String activity;
        private LocalDateTime createdAt;
        private boolean isBookamrk;
        private int favorites;
        private boolean isFavorite;
        private int open;
        private boolean isOpen;

        private List<String> hashtagList;
        private List<String> placeImgUrlList;

        private List<PlaceReviewResponse> placeReviewResponseList;
    }

    /**
     * 공간 상세 조회 페이지에서 한줄평을 전달하기 위한 DTO
     */
    @Data
    @AllArgsConstructor
    static class PlaceReviewResponse {
        private Long id;
        private String userIndex;
        private String userNickName;
        private String userImgUrl;
        private String contents;
        private LocalDateTime createdAt;
        private boolean isFavorite;
    }
}
