package kr.yeoksi.ours.oursserver.place.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kr.yeoksi.ours.oursserver.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PlaceReview {

    private Long id;
    private String placeId;

    private User user;

    private String contents;

    private Integer likes;
    private Boolean isLiked;

    private LocalDateTime createdAt;

}
