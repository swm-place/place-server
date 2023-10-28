package kr.yeoksi.ours.oursserver.magazine.adapter.out.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;


@Entity(name = "course_magazine")
@Getter @Setter
@Builder @AllArgsConstructor
@NoArgsConstructor
public class CourseMagazineJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_magazine_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @NotBlank
    @Column(length = 50)
    @Size(max = 50)
    private String title;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String contents;

    @Builder.Default
    @OneToMany(mappedBy = "courseMagazine", cascade = CascadeType.ALL)
    private List<PlaceInCourseMagazineJpaEntity> placesInCourseMagazine = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public static CourseMagazineJpaEntity from(CourseMagazine courseMagazine) {
        CourseMagazineJpaEntity jpaEntity = CourseMagazineJpaEntity.builder()
                .id(courseMagazine.getId())
                .user(courseMagazine.getUser())
                .title(courseMagazine.getTitle())
                .contents(courseMagazine.getContents())
                .build();

        List<PlaceInCourseMagazineJpaEntity> placesInCourseMagazine = new ArrayList<>(
                courseMagazine.getPlacesInCourseMagazine().stream()
                        .map(placeInCourseMagazine -> PlaceInCourseMagazineJpaEntity.from(placeInCourseMagazine, jpaEntity))
                        .toList()
        );

        jpaEntity.setPlacesInCourseMagazine(placesInCourseMagazine);
        return jpaEntity;
    }

    public CourseMagazine toCourseMagazine() {
        List<PlaceInCourseMagazine> placesInCourseMagazine = new ArrayList<>(
                this.placesInCourseMagazine.stream()
                        .map(PlaceInCourseMagazineJpaEntity::toPlaceInCourse)
                        .toList()
        );

        return CourseMagazine.builder()
                .id(this.id)
                .user(this.user)
                .title(this.title)
                .contents(this.contents)
                .placesInCourseMagazine(placesInCourseMagazine)
                .build();
    }

}
