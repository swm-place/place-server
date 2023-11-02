package kr.yeoksi.ours.oursserver.magazine.adapter.in.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class FavoriteCourseMagazineResponse {
    private Long id;

    private String title;
    private PlaceWithCourseMagazineResponse firstPlace;

    private LocalDateTime createdAt;
}
