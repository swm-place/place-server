package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.yeoksi.ours.oursserver.domain.idclass.PlaceToPlaceId;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@IdClass(PlaceToPlaceId.class)
public class PlaceToPlace {

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(referencedColumnName = "places_in_course_index", name = "places_in_course_index_left")
    private PlacesInCourse leftPlace;

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(referencedColumnName = "places_in_course_index", name = "places_in_course_index_right")
    private PlacesInCourse rightPlace;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "transportaion_index")
    private Transportation transportation;

    @Column(name = "required_time")
    private Integer requiredTime;
}
