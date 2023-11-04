package kr.yeoksi.ours.oursserver.magazine.adapter.in.response;

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
}
