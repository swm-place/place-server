package kr.yeoksi.ours.oursserver.place.domain;

import kr.yeoksi.ours.oursserver.common.domain.Location;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class Place {

    private String id;

    private String name;
    private String category;
    private String summary;

    private String address;
    private String roadAddress;
    private String phoneNumber;

    private Location location;

    private List<String> hashtags;

    private List<PlaceOpeningHour> openingHours;
    private List<String> openingHoursText;
    private Boolean openNow;

    private List<PlacePhotoRef> photos;
    private List<PlaceReview> reviews;

    private Boolean isFavorite;

}
