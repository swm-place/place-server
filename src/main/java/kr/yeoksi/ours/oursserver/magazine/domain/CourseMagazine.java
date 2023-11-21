package kr.yeoksi.ours.oursserver.magazine.domain;


import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseMagazine {

    private Long id;
    private User user;

    private String title;
    private String contents;

    @Builder.Default
    private List<PlaceInCourseMagazine> placesInCourseMagazine = new ArrayList<>();

    private LocalDateTime createdAt;

    private Boolean isFavorite;

}
