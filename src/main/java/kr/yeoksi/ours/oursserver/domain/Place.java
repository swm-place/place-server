package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Place {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_index")
    private Long id;

    @NotBlank
    @Column(name = "elastic_id", length = 30)
    private String elasticId;

    @NotBlank
    @Column(length = 30)
    @Size(max = 30)
    private String name;

    @Column(length = 20)
    @Size(max = 20)
    private String category;

    private String imgUrl;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaceImg> placeImgs = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    private List<PlaceFavorite> placeFavorites = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    private List<PlaceOpen> placeOpens = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    private List<PlaceReview> placeReviews = new ArrayList<>();
}
