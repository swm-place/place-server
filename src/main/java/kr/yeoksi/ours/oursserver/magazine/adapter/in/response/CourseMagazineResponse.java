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


}
