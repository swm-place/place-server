package kr.yeoksi.ours.oursserver.controller;

import kr.yeoksi.ours.oursserver.domain.Place;
import kr.yeoksi.ours.oursserver.domain.Response;
import kr.yeoksi.ours.oursserver.domain.dto.place.request.ReadPlaceFromElastic;
import kr.yeoksi.ours.oursserver.domain.dto.place.response.ReadPlaceResponse;
import kr.yeoksi.ours.oursserver.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PlaceApiController {

    private final PlaceService placeService;

    /**
     * 장소 세부정보 조회
     */
    @GetMapping("/place/{placeIndex}")
    public ResponseEntity<Response<ReadPlaceResponse>> readPlace (
            @PathVariable("placeIndex") String placeId) throws Exception {

        Place place = placeService.findByElasticId(placeId);
        ReadPlaceFromElastic readPlaceFromElastic = placeService.readPlaceFromElastic(placeId);

        return ResponseEntity.ok().body(
                Response.success(
                        new ReadPlaceResponse(
                                place.getId(),
                                place.getName(),
                                place.getImgUrl(),
                                readPlaceFromElastic.getHashtagList(),
                                readPlaceFromElastic.getSummary(),
                                readPlaceFromElastic.getRoadAddress(),
                                readPlaceFromElastic.getAddress()
                        )
                )
        );
    }

    @GetMapping("/connection")
    public ResponseEntity<Response<String>> checkConnection() {

        return ResponseEntity.ok().body(
                Response.success(
                        "공간 상세정보 조회1"
                )
        );
    }

    /**
     * id로 공간 하나 조회하기.
     */
    /*
    @GetMapping("/place/{placeIndex}")
    public ResponseEntity<Response<PlaceResponse>> readPlace(
            @RequestHeader("X-User-Uid") String uid,
            @PathVariable("placeIndex") Long id,
            @RequestParam("reviewCount") int reviewCount) {

        // 공간 정보 조회
        Place place = placeService.findById(id);

        // 공간에 매핑된 이미지 조회
        List<String> placeImgUrlList = placeService.getImgUrlList(id);

        // 공간에 매핑된 해시태그 조회
        List<String> hashtagList = placeService.getHashtagList(id);

        // 북마크 여부 확인
        //boolean isBookmark = placeService.checkBookmark(uid, id);

        // 해당 공간의 좋아요 개수 확인
        int favorites = placeService.getFavoriteCount(id);

        // 유저의 좋아요 여부 확인
        boolean isFavorite = placeService.checkFavorite(uid, id);

        // 현재 운영중이라고 응답한 유저의 수 조회
        int open = placeService.getOpenCount(id);

        // 유저의 운영중 응답 여부 확인
        boolean isOpen = placeService.checkOpen(uid, id);

        // 공간에 매핑된 한줄평들 조회하기
        List<PlaceReview> placeReviewList = placeService.getPlaceReviewList(id, reviewCount);
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
                                false,
                                favorites,
                                isFavorite,
                                open,
                                isOpen,
                                hashtagList,
                                placeImgUrlList,
                                placeReviewResponseList
                        )));
    }

     */
}