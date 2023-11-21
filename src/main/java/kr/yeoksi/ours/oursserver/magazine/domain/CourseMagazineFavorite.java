package kr.yeoksi.ours.oursserver.magazine.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseMagazineFavorite {

    private Long id;

    private String userId;
    private Long courseMagazineId;

    private CourseMagazine courseMagazine;

    private LocalDateTime createdAt;

}
