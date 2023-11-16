package kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.others.domain.Place;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "place_in_course")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceInCourseJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "places_in_course_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "course_index")
    private CourseJpaEntity course;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_index")
    private Place place;

    @NotNull
    @Column(name = "visit_order")
    private int visitOrder;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "time_required")
    private int timeRequired;

    @Column(name = "day")
    private int day;

    @Column(name = "transportation_time")
    private int transportationTime;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    public static PlaceInCourseJpaEntity from(PlaceInCourse placeInCourse, CourseJpaEntity courseJpaEntity) {
        return PlaceInCourseJpaEntity.builder()
                .id(placeInCourse.getId())
                .course(courseJpaEntity)
                .place(placeInCourse.getPlace())
                .visitOrder(placeInCourse.getOrder())
                .startAt(placeInCourse.getStartAt())
                .timeRequired(placeInCourse.getTimeRequired())
                .day(placeInCourse.getDay())
                .transportationTime(placeInCourse.getTransportationTime())
                .build();
    }

    public PlaceInCourse toPlaceInCourse() {
        return PlaceInCourse.builder()
                .id(this.id)
                .courseId(this.course.getId())
                .place(this.place)
                .order(this.visitOrder)
                .startAt(this.startAt)
                .timeRequired(this.timeRequired)
                .day(this.day)
                .transportationTime(this.transportationTime)
                .createdAt(this.createdAt)
                .build();
    }

}
