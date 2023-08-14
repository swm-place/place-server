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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @NotBlank
    @Column(length = 30, columnDefinition = "VARCHAR(30) CHARACTER SET UTF8")
    @Size(max = 30)
    private String name;

    @Column(length = 20, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Size(max = 20)
    private String category;

    @Column(name = "phone_number", length = 15)
    @Size(max = 15)
    private String phoneNumber;

    @NotBlank
    @Column(length = 100, columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
    @Size(max = 100)
    private String address;

    @NotNull
    private double longitude;

    @NotNull
    private double latitude;

    @NotNull
    @Column(name = "location_code")
    private int locationCode;

    @Column(columnDefinition = "TEXT CHARACTER SET UTF8")
    private String activity;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceImg> placeImgs = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceFavorite> placeFavorites = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceBookmark> placeBookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceOpen> placeOpens = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceReview> placeReviews = new ArrayList<>();
}
