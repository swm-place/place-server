package kr.yeoksi.ours.oursserver.course.adapter.in.response;

import kr.yeoksi.ours.oursserver.others.domain.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
@Builder
public class PlaceWithCourseResponse {
    private String id;
    private String name;
    private String category;
    private String imgUrl;
    private Map<String, Object> openTime;
    private List<PlaceImg> placeImgs;
    private List<Menu> menus;
    private List<PlaceFavorite> placeFavorites;
    private List<PlaceOpen> placeOpens;
    private List<PlaceReview> placeReviews;


    public static PlaceWithCourseResponse from(Place place) {
        return PlaceWithCourseResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .category(place.getCategory())
                .imgUrl(place.getImgUrl())
                .openTime(place.getOpenTime())
                .placeImgs(place.getPlaceImgs())
                .menus(place.getMenus())
                .placeFavorites(place.getPlaceFavorites())
                .placeOpens(place.getPlaceOpens())
                .placeReviews(place.getPlaceReviews())
                .build();
    }
}
