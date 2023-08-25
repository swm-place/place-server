package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class PlaceToPlace {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_to_place_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(referencedColumnName = "places_in_course_index", name = "places_in_course_index_left")
    private PlaceInCourse leftPlace;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(referencedColumnName = "places_in_course_index", name = "places_in_course_index_right")
    private PlaceInCourse rightPlace;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "transportaion_index")
    private Transportation transportation;

    @Column(name = "required_time")
    private Integer requiredTime;
}
