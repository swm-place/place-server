package kr.yeoksi.ours.oursserver.others.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Menu {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_index")
    private Long id;

    // TODO: @JsonIgnore 대신 응답용 DTO 사용
    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_index")
    private Place place;

    @NotBlank
    @Column(length = 30)
    @Size(max = 30)
    private String name;

    @NotNull
    private int price;

    @Column(name = "img_url")
    private String imgUrl;
}
