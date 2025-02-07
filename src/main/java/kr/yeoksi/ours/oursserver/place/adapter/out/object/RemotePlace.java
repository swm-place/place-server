package kr.yeoksi.ours.oursserver.place.adapter.out.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kr.yeoksi.ours.oursserver.common.domain.Location;
import kr.yeoksi.ours.oursserver.place.domain.Place;
import kr.yeoksi.ours.oursserver.place.domain.PlaceOpeningHour;
import kr.yeoksi.ours.oursserver.place.domain.PlacePhotoRef;
import kr.yeoksi.ours.oursserver.place.domain.PlaceReview;
import lombok.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RemotePlace {

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


    public Place toPlace() {
        return Place.builder()
                .id(id)
                .name(name)
                .category(category)
                .summary(summary)
                .address(address)
                .roadAddress(roadAddress)
                .phoneNumber(phoneNumber)
                .location(location)
                .hashtags(hashtags)
                .openingHours(openingHours)
                .openingHoursText(openingHoursText)
                .openNow(openNow)
                .photos(photos)
                .reviews(reviews)
                .isFavorite(isFavorite)
                .build();
    }

}
