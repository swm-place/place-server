package kr.yeoksi.ours.oursserver.controller;

import kr.yeoksi.ours.oursserver.domain.Place;
import kr.yeoksi.ours.oursserver.domain.Response;
import kr.yeoksi.ours.oursserver.service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<Response<PlaceResponse>> readPlace(@PathVariable("placeIndex") Long id) {

        // 유저가 권한이 있는 유저인지 -> Spring Security에서 검증

        Place place = placeService.findById(id);
        List<String> placeImgUrls = placeService.getImgUrlList(id);

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
