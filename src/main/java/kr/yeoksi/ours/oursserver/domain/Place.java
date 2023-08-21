package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Place {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_index")
    private Long id;

    @NotBlank
    @Column(length = 30)
    private String elastic_index;

    @NotBlank
    @Column(length = 30)
    @Size(max = 30)
    private String name;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceImg> placeImgs = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceFavorite> placeFavorites = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceOpen> placeOpens = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceReview> placeReviews = new ArrayList<>();
}
