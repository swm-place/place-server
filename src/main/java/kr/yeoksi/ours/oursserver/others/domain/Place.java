package kr.yeoksi.ours.oursserver.others.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Column(length = 30)
    @Size(max = 30)
    private String name;

    @Column(length = 20)
    @Size(max = 20)
    private String category;

    private String imgUrl;

    @Type(JsonType.class)
    @Column(name = "open_time", columnDefinition = "longtext")
    private Map<String, Object> openTime;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PlaceImg> placeImgs = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Menu> menus = new ArrayList<>();

    // TODO: @JsonIgnore 대신 응답용 DTO 사용
    @JsonIgnore
    @OneToMany(mappedBy = "place")
    @Builder.Default
    private List<PlaceFavorite> placeFavorites = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    @Builder.Default
    private List<PlaceOpen> placeOpens = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    @Builder.Default
    private List<PlaceReview> placeReviews = new ArrayList<>();
}
