package kr.yeoksi.ours.oursserver.magazine.adapter.in.request;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class CourseMagazineRequest {

    private String title;
    private String contents;

    private List<PlaceInCourseMagazineRequest> placesInCourseMagazine = new ArrayList<>();


    public CourseMagazine toCourseMagazine() {
        List<PlaceInCourseMagazine> placesInCourseMagazine = new ArrayList<>(
            this.placesInCourseMagazine.stream()
                .map(PlaceInCourseMagazineRequest::toPlaceInCourseMagazine)
                .toList()
        );

        return CourseMagazine.builder()
                .title(title)
                .contents(contents)
                .placesInCourseMagazine(placesInCourseMagazine)
                .build();
    }
}
