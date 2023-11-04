package kr.yeoksi.ours.oursserver.magazine.adapter.in.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class CourseMagazineListItemResponse {

    private Long id;

    private String title;
    private String contents;

    private String imgUrl;

    private LocalDateTime createdAt;


    public static CourseMagazineListItemResponse from(CourseMagazineResponse courseMagazineResponse) {
        CourseMagazineListItemResponse response = CourseMagazineListItemResponse.builder()
                .id(courseMagazineResponse.getId())
                .title(courseMagazineResponse.getTitle())
                .contents(courseMagazineResponse.getContents())
                .createdAt(courseMagazineResponse.getCreatedAt())
                .build();

        if (!courseMagazineResponse.getPlacesInCourseMagazine().isEmpty()) {
            response.setImgUrl(courseMagazineResponse.getPlacesInCourseMagazine().get(0).getPlace().getImgUrl());
        }

        return response;
    }
}
