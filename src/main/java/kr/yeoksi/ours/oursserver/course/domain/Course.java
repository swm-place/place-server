package kr.yeoksi.ours.oursserver.course.domain;

import kr.yeoksi.ours.oursserver.others.domain.User;
import kr.yeoksi.ours.oursserver.utils.EntityUtils;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    private Long id;
    private User user;

    private String title;
    private String description;

    @Builder.Default
    private List<PlaceInCourse> placesInCourse = new ArrayList<>();

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private boolean inProgress;
    private boolean isFinished;

    private LocalDateTime createdAt;

    @Builder.Default
    private List<CourseBookmark> bookmarks = new ArrayList<>();


    public void update(Course source) {
        EntityUtils.updateNotNullProperties(this, source);
    }
}
