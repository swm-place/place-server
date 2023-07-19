package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Transportation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transportation_index")
    private Long id;

    @NotBlank
    private String name;
}
