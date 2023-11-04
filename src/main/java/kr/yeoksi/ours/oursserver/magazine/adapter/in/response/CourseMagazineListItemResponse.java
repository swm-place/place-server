package kr.yeoksi.ours.oursserver.magazine.adapter.in.response;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
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


    public static CourseMagazineListItemResponse from(CourseMagazine courseMagazine) {
        CourseMagazineListItemResponse response = CourseMagazineListItemResponse.builder()
                .id(courseMagazine.getId())
                .title(courseMagazine.getTitle())
                .contents(courseMagazine.getContents())
                .createdAt(courseMagazine.getCreatedAt())
                .build();

        if (!courseMagazine.getPlacesInCourseMagazine().isEmpty()) {
            response.setImgUrl(courseMagazine.getPlacesInCourseMagazine().get(0).getPlace().getImgUrl());
        }

        return response;
    }
}
