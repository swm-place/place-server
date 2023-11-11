package kr.yeoksi.ours.oursserver.course.domain;

import kr.yeoksi.ours.oursserver.others.domain.Place;
import kr.yeoksi.ours.oursserver.utils.EntityUtils;
import lombok.*;

import java.time.LocalDateTime;


@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceInCourse {
    private Long id;
    private Long courseId;

    private Place place;
    private kr.yeoksi.ours.oursserver.place.domain.Place remotePlace;

    private int day;
    private int order;

    private LocalDateTime startAt;
    private int timeRequired;
    private int transportationTime;

    private LocalDateTime createdAt;


    public void update(PlaceInCourse source) {
        EntityUtils.updateNotNullProperties(this, source);
    }
}
