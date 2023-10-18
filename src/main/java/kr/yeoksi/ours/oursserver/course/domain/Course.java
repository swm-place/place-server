package kr.yeoksi.ours.oursserver.course.domain;

import kr.yeoksi.ours.oursserver.others.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter @Setter
public class Course {
    private Long id;
    private User user;

    private String title;
    private String description;
    private List<PlaceInCourse> placesInCourse;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private boolean inProgress;
    private boolean isFinished;

    private LocalDateTime createdAt;
}
