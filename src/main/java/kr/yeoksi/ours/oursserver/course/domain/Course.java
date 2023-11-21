package kr.yeoksi.ours.oursserver.course.domain;

import kr.yeoksi.ours.oursserver.others.domain.User;
import kr.yeoksi.ours.oursserver.utils.EntityUtils;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@NoArgsConstructor
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

    private String routesJson;


    public void update(Course source) {
        List<PlaceInCourse> placesInCourse = new ArrayList<>(this.getPlacesInCourse());

        for (PlaceInCourse sourcePlaceInCourse : source.getPlacesInCourse()) {
            boolean updated = false;

            for (PlaceInCourse placeInCourse : placesInCourse) {
                if (placeInCourse.getId() != null && placeInCourse.getId().equals(sourcePlaceInCourse.getId())) {
                    placeInCourse.update(sourcePlaceInCourse);
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                placesInCourse.add(sourcePlaceInCourse);
            }
        }

        EntityUtils.updateNotNullProperties(this, source);
        this.placesInCourse = placesInCourse;
    }
}
