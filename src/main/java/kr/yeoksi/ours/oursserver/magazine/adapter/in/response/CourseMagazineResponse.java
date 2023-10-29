package kr.yeoksi.ours.oursserver.magazine.adapter.in.response;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class CourseMagazineResponse {

    private Long id;
    private UserInCourseMagazineResponse user;

    private String title;
    private String contents;

    @Builder.Default
    private List<PlaceInCourseMagazineResponse> placesInCourseMagazine = new ArrayList<>();

    private LocalDateTime createdAt;


    public static CourseMagazineResponse from(CourseMagazine courseMagazine) {
        List<PlaceInCourseMagazineResponse> placesInCourseMagazine = new ArrayList<>(
                courseMagazine.getPlacesInCourseMagazine()
                        .stream().map(PlaceInCourseMagazineResponse::from)
                        .toList()
        );

        return CourseMagazineResponse.builder()
                .id(courseMagazine.getId())
                .user(UserInCourseMagazineResponse.from(courseMagazine.getUser()))
                .title(courseMagazine.getTitle())
                .contents(courseMagazine.getContents())
                .placesInCourseMagazine(placesInCourseMagazine)
                .createdAt(courseMagazine.getCreatedAt())
                .build();
    }
}
