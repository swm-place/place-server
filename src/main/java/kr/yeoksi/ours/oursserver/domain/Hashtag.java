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
public class Hashtag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_index")
    private Long id;

    @NotBlank
    @Column(length = 30, columnDefinition = "VARCHAR(30) CHARACTER SET UTF8")
    @Size(max = 30)
    private String name;

    @NotBlank
    @Column(name = "img_url")
    private String imgUrl;

    @OneToMany(mappedBy = "hashtag", cascade = CascadeType.ALL)
    private List<HashtagAtPlace> mappedPlaces = new ArrayList<>();
}
