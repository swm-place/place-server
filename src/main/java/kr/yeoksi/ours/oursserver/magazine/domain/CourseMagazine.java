package kr.yeoksi.ours.oursserver.magazine.domain;


import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.others.domain.User;
import kr.yeoksi.ours.oursserver.utils.EntityUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseMagazine {

    private Long id;
    private User user;

    private String title;
    private String contents;

    @Builder.Default
    private List<PlaceInCourseMagazine> placesInCourseMagazine = new ArrayList<>();

    private LocalDateTime createdAt;

    private Boolean isFavorite;


    public void update(CourseMagazine source) {
        List<PlaceInCourseMagazine> placesInCourseMagazine = this.getPlacesInCourseMagazine();

        for (PlaceInCourseMagazine sourcePlaceInCourseMagazine : source.getPlacesInCourseMagazine()) {
            boolean updated = false;

            for (PlaceInCourseMagazine placeInCourseMagazine : placesInCourseMagazine) {
                if (placeInCourseMagazine.getId() != null && placeInCourseMagazine.getId().equals(sourcePlaceInCourseMagazine.getId())) {
                    placeInCourseMagazine.update(sourcePlaceInCourseMagazine);
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                placesInCourseMagazine.add(sourcePlaceInCourseMagazine);
            }
        }

        EntityUtils.updateNotNullProperties(this, source);
        this.placesInCourseMagazine = placesInCourseMagazine;
    }

}
