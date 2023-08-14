package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Transportation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transportation_index")
    private Long id;

    @NotBlank
    @Column(length = 20, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Size(max = 20)
    private String name;
}
