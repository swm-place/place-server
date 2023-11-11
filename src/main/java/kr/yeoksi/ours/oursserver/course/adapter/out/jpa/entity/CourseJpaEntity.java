package kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "course")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CourseJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @NotBlank
    @Column(length = 50)
    @Size(max = 50)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<PlaceInCourseJpaEntity> placesInCourse = new ArrayList<>();

    @Column(name = "routes_json", columnDefinition = "TEXT")
    private String routesJson;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "in_progress", columnDefinition = "TINYINT(1)")
    private boolean inProgress;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_finished", columnDefinition = "TINYINT(1)")
    private boolean isFinished;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


    public static CourseJpaEntity from(Course course) {
        CourseJpaEntity courseJpaEntity = CourseJpaEntity.builder()
                .id(course.getId())
                .user(course.getUser())
                .title(course.getTitle())
                .description(course.getDescription())
                .routesJson(course.getRoutesJson())
                .startAt(course.getStartAt())
                .endAt(course.getEndAt())
                .inProgress(course.isInProgress())
                .isFinished(course.isFinished())
                .createdAt(course.getCreatedAt())
                .build();

        List<PlaceInCourseJpaEntity> placesInCourse = new ArrayList<>(
                course.getPlacesInCourse().stream()
                        .map(placeInCourse -> PlaceInCourseJpaEntity.from(placeInCourse, courseJpaEntity))
                        .toList());

        courseJpaEntity.setPlacesInCourse(placesInCourse);
        return courseJpaEntity;
    }

    public Course toCourse() {
        List<PlaceInCourse> placesInCourse = new ArrayList<>(
                this.placesInCourse.stream()
                        .map(PlaceInCourseJpaEntity::toPlaceInCourse)
                        .toList());

        return Course.builder()
                .id(this.id)
                .user(this.user)
                .title(this.title)
                .description(this.description)
                .placesInCourse(placesInCourse)
                .routesJson(this.routesJson)
                .startAt(this.startAt)
                .endAt(this.endAt)
                .inProgress(this.inProgress)
                .isFinished(this.isFinished)
                .createdAt(this.createdAt)
                .build();
    }
}
