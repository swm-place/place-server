package kr.yeoksi.ours.oursserver.magazine.adapter.out.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
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
    @Column(name = "order")
    private int order;


    public static PlaceInCourseMagazineJpaEntity from(PlaceInCourseMagazine placeInCourseMagazine) {
        return PlaceInCourseMagazineJpaEntity.builder()
                .id(placeInCourseMagazine.getId())
                .courseMagazine(CourseMagazineJpaEntity.from(placeInCourseMagazine.getCourseMagazine()))
                .place(placeInCourseMagazine.getPlace())
                .contents(placeInCourseMagazine.getContents())
                .order(placeInCourseMagazine.getOrder())
                .build();
    }

}
