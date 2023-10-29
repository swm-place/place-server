package kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import kr.yeoksi.ours.oursserver.others.domain.Place;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;


@Entity(name = "place_in_course_magazine")
@Getter @Setter
@Builder @AllArgsConstructor
@NoArgsConstructor
public class PlaceInCourseMagazineJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_in_course_magazine_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "course_magazine_index")
    private CourseMagazineJpaEntity courseMagazine;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_index")
    private Place place;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String contents;

    @NotNull
    @Column(name = "visit_order")
    private int visitOrder;


    public static PlaceInCourseMagazineJpaEntity from(PlaceInCourseMagazine placeInCourseMagazine, CourseMagazineJpaEntity courseMagazineJpaEntity) {
        return PlaceInCourseMagazineJpaEntity.builder()
                .id(placeInCourseMagazine.getId())
                .courseMagazine(courseMagazineJpaEntity)
                .place(placeInCourseMagazine.getPlace())
                .contents(placeInCourseMagazine.getContents())
                .visitOrder(placeInCourseMagazine.getOrder())
                .build();
    }

    public PlaceInCourseMagazine toPlaceInCourseMagazine() {
        return PlaceInCourseMagazine.builder()
                .id(this.id)
                .place(this.place)
                .contents(this.contents)
                .order(this.visitOrder)
                .build();
    }

}
