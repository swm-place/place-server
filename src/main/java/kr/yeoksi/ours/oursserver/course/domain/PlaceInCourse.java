package kr.yeoksi.ours.oursserver.course.domain;

import kr.yeoksi.ours.oursserver.others.domain.Place;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter @Setter
public class PlaceInCourse {
    private Long id;

    private Place place;

    private int day;
    private int order;

    private LocalDateTime startAt;
    private int timeRequired;
    private int transportationTime;
}
