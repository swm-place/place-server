package kr.yeoksi.ours.oursserver.place.domain;

import kr.yeoksi.ours.oursserver.common.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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


    public kr.yeoksi.ours.oursserver.others.domain.Place toOldPlace() {
        return kr.yeoksi.ours.oursserver.others.domain.Place.builder()
                .id(this.id)
                .name(this.name)
                .category(this.category)
                .imgUrl(this.photos.get(0).getUrl())
                .build();
    }

}
