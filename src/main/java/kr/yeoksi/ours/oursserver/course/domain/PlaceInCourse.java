package kr.yeoksi.ours.oursserver.course.domain;

import kr.yeoksi.ours.oursserver.others.domain.Place;
import lombok.*;

import java.time.LocalDateTime;


@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceInCourse {
    private Long id;

    private Place place;

    private int day;
    private int order;

    private LocalDateTime startAt;
    private int timeRequired;
    private int transportationTime;

    private LocalDateTime createdAt;
}
