package kr.yeoksi.ours.oursserver.course.domain;

import kr.yeoksi.ours.oursserver.others.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter @Setter
public class Course {
    private Long id;
    private User user;

    private String title;
    private PlaceInCourse[] places;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private boolean inProgress;
    private boolean isFinished;

    private LocalDateTime createdAt;
}
