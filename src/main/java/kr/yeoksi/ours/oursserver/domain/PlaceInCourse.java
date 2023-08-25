package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class PlaceInCourse {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "places_in_course_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "course_index")
    private Course course;

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

    private int day;

    @Column(name = "transportation_time")
    private int transportationTime;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
