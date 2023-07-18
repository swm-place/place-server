package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import kr.yeoksi.ours.oursserver.domain.idclass.PlacesInCourseId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@IdClass(PlacesInCourseId.class)
public class PlacesInCourse {

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "course_index")
    private Course course;

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_index")
    private Place place;

    private Integer order;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "time_required")
    private Integer timeRequired;

    private Integer day;

    @Column(name = "transportation_time")
    private Integer transportationTime;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
