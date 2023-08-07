package kr.yeoksi.ours.oursserver.controller;

import kr.yeoksi.ours.oursserver.domain.Hashtag;
import kr.yeoksi.ours.oursserver.domain.Place;
import kr.yeoksi.ours.oursserver.domain.Response;
import kr.yeoksi.ours.oursserver.service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
        List<String> placeImgUrls = placeService.getImgUrlList(id);

        // 공간에 매핑된 해시태그 조회
        List<Hashtag> hashtags = placeService.getHashtagList(id);

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
                                placeImgUrls
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

        private List<String> placeImgUrlList;
    }
}
