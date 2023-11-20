package kr.yeoksi.ours.oursserver.others.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.yeoksi.ours.oursserver.place.domain.PlacePhotoRef;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Place {

    @Id
    @Column(name = "place_index")
    private String id;

    @NotBlank
    @Column(length = 255)
    @Size(max = 255)
    private String name;

    @Column(length = 20)
    @Size(max = 20)
    private String category;

    private String imgUrl;

    @Type(JsonType.class)
    @Column(name = "open_time", columnDefinition = "longtext")
    private Map<String, Object> openTime;

    @JsonIgnore
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PlaceImg> placeImgs = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Menu> menus = new ArrayList<>();

    // TODO: @JsonIgnore 대신 응답용 DTO 사용
    @JsonIgnore
    @OneToMany(mappedBy = "place")
    @Builder.Default
    private List<PlaceFavorite> placeFavorites = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "place")
    @Builder.Default
    private List<PlaceOpen> placeOpens = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "place")
    @Builder.Default
    private List<PlaceReview> placeReviews = new ArrayList<>();


    // TODO: 이미지 URI는 서비스에서 환경변수 이용해 주입
    // 현재 장소 사진은 다른 서버에 의존하고 있어, 해당 서버로의 요청 URI를 포함
    public String getImgUrl() {
        return "/api-recommender/place-photo/place/" + id;
    }

    public kr.yeoksi.ours.oursserver.place.domain.Place toOrgPlace() {
        List<PlacePhotoRef> photos = new ArrayList<>();
        photos.add(PlacePhotoRef.builder()
                .url(this.imgUrl)
                .build());

        return kr.yeoksi.ours.oursserver.place.domain.Place.builder()
                .id(this.id)
                .name(this.name)
                .category(this.category)
                .photos(photos)
                .build();
    }
}
