package kr.yeoksi.ours.oursserver.course.adapter.in.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kr.yeoksi.ours.oursserver.common.domain.Location;
import kr.yeoksi.ours.oursserver.others.domain.Place;
import kr.yeoksi.ours.oursserver.place.domain.PlaceOpeningHour;
import kr.yeoksi.ours.oursserver.place.domain.PlacePhotoRef;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PlaceWithCourseResponse {

    private String id;

    private String name;
    private String category;
    private String imgUrl;

    private Location location;

    private List<PlaceOpeningHour> openingHours;
    private List<String> openingHoursText;


    public static PlaceWithCourseResponse from(Place place) {
        return PlaceWithCourseResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .category(place.getCategory())
                .imgUrl(place.getImgUrl())
                .build();
    }

    public static PlaceWithCourseResponse from(kr.yeoksi.ours.oursserver.place.domain.Place place) {
        return PlaceWithCourseResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .category(place.getCategory())
                .imgUrl(place.getPhotos().get(0).getUrl())
                .location(place.getLocation())
                .openingHours(place.getOpeningHours())
                .openingHoursText(place.getOpeningHoursText())
                .build();
    }
}
