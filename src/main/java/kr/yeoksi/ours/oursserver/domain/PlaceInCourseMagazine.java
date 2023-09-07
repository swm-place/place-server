package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class PlaceInCourseMagazine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_in_course_magazine_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "course_magazine_index")
    private CourseMagazine courseMagazine;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_index")
    private Place place;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String contents;

    @NotNull
    @Column(name = "visit_order")
    private int visitOrder;
}
