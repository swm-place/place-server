package kr.yeoksi.ours.oursserver.course.adapter.out.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.yeoksi.ours.oursserver.others.domain.Course;
import kr.yeoksi.ours.oursserver.others.domain.Place;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class PlaceInCourseJPAEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "places_in_course_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "course_index")
    private CourseJPAEntity course;

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
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
