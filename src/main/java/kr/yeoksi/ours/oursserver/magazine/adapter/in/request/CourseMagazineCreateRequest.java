package kr.yeoksi.ours.oursserver.magazine.adapter.in.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class CourseMagazineCreateRequest {
    private String title;
    private String contents;
    private List<PlaceInCourseMagazineCreateRequest> placesInCourseMagazine;
}
