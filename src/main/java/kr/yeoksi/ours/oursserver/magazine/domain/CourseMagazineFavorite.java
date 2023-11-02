package kr.yeoksi.ours.oursserver.magazine.domain;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class CourseMagazineFavorite {

    private Long id;

    private Long userId;
    private Long courseMagazineId;

    private LocalDateTime createdAt;

}
