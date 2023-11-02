package kr.yeoksi.ours.oursserver.magazine.adapter.in.response;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Comparator;


@Data
@Builder
public class FavoriteCourseMagazineResponse {
    private Long id;

    private String title;
    private PlaceWithCourseMagazineResponse firstPlace;

    private LocalDateTime createdAt;


    public static FavoriteCourseMagazineResponse from(CourseMagazine courseMagazine) {
        return FavoriteCourseMagazineResponse.builder()
                .id(courseMagazine.getId())
                .title(courseMagazine.getTitle())
                .firstPlace(PlaceWithCourseMagazineResponse.from(
                        courseMagazine.getPlacesInCourseMagazine().stream()
                                .sorted(Comparator.comparingInt(PlaceInCourseMagazine::getOrder))
                                .toList().get(0)
                                .getPlace())
                )
                .createdAt(courseMagazine.getCreatedAt())
                .build();
    }
}
