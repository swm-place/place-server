package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Place {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_index")
    private Long id;

    @NotBlank
    private String name;

    private String category;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank
    private String address;

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

    @NotNull
    @Column(name = "location_code")
    private Integer locationCode;

    private String activity;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceImg> placeImgs = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();
}
