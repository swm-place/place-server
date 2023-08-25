package kr.yeoksi.ours.oursserver.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import kr.yeoksi.ours.oursserver.domain.Place;
import kr.yeoksi.ours.oursserver.domain.Response;
import kr.yeoksi.ours.oursserver.domain.dto.place.request.ReadPlaceFromElastic;
import kr.yeoksi.ours.oursserver.domain.dto.place.response.ReadPlaceResponse;
import kr.yeoksi.ours.oursserver.domain.dto.place.response.ReadPlaceReviewResponse;
import kr.yeoksi.ours.oursserver.service.PlaceService;
import kr.yeoksi.ours.oursserver.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaceApiController {

    private final PlaceService placeService;
    private final UserService userService;

    /**
     * 장소 세부정보 조회
     */
    @GetMapping("/place/{placeIndex}")
    public ResponseEntity<Response<ReadPlaceResponse>> readPlace (
            @RequestHeader("X-User-Uid") String userId,
            @PathVariable("placeIndex") String placeId,
            @RequestParam(value = "reviewCount") int reviewCount,
            @RequestParam(value = "imgCount") int imgCount) throws Exception {

        // DB에서 장소 정보 조회하기.
        Place place = placeService.findByElasticId(placeId);

        // 엘라스틱에서 장소 정보 조회하기.
        ReadPlaceFromElastic readPlaceFromElastic = placeService.readPlaceFromElastic(placeId);

        // 장소 북마크 여부 조회
        boolean isBookmark = userService.checkBookmark(userId, place.getId());

        // 장소 좋아요 여부 확인
        boolean isFavorite = userService.checkFavorite(userId, place.getId());

        // 장소의 좋아요 개수 조회
        int favoriteCnt = placeService.getFavoriteCount(place.getId());

        // 운영중 응답 여부 확인
        boolean isOpen = userService.checkOpen(userId, place.getId());

        // 운영중 응답자 수 조회
        int openCnt = placeService.getOpenCount(place.getId());

        // 한줄평 조회
        List<ReadPlaceReviewResponse> placeReviewResponseList = placeService.getPlaceReviewList(userId, place.getId(), reviewCount);

        // 사진 조회
        List<String> imgUrlList = placeService.getImgUrlList(place.getId(), imgCount);

        return ResponseEntity.ok().body(
                Response.success(
                        new ReadPlaceResponse(
                                place.getId(),
                                place.getName(),
                                place.getImgUrl(),
                                readPlaceFromElastic.getHashtagList(),
                                isBookmark,
                                isFavorite,
                                favoriteCnt,
                                readPlaceFromElastic.getSummary(),
                                readPlaceFromElastic.getRoadAddress(),
                                readPlaceFromElastic.getAddress(),
                                isOpen,
                                openCnt,
                                placeReviewResponseList,
                                imgUrlList
                        )
                )
        );
    }

    /**
     * 공간 정보 저장
     */
    @PostMapping("/place")
    public void uploadPlace(@RequestBody @Valid CreatePlaceRequest request) {

        Place place = new Place();
        place.setName(request.getName());
        place.setElasticId(request.getElasticId());
        place.setCategory(request.getCategory());
        place.setImgUrl(request.getImgUrl());

        placeService.createPlace(place);
    }


    @GetMapping("/connection")
    public ResponseEntity<Response<String>> checkConnection() {

        return ResponseEntity.ok().body(
                Response.success(
                        "공간 정보 조회 api"
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


        // 공간에 매핑된 이미지 조회
        List<String> placeImgUrlList = placeService.getImgUrlList(id);


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

    /**
     * 장소 데이터 저장을 위한 DTO
     */
    @Data
    static class CreatePlaceRequest {

        @NotBlank
        private String elasticId;

        @NotBlank
        private String name;

        private String category;

        private String imgUrl;
    }
}