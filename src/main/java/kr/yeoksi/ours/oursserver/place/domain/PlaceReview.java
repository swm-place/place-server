package kr.yeoksi.ours.oursserver.place.domain;

import kr.yeoksi.ours.oursserver.user.domain.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class PlaceReview {

    private Long id;
    private String placeId;

    private User user;

    private String contents;

    private Integer likes;
    private Boolean isLiked;

    private LocalDateTime createdAt;

}
