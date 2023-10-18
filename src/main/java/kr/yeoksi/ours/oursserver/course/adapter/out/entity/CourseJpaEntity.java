package kr.yeoksi.ours.oursserver.course.adapter.out.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@RequiredArgsConstructor
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
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public static CourseJpaEntity from(Course course) {
        return CourseJpaEntity.builder()
                .id(course.getId())
                .user(course.getUser())
                .title(course.getTitle())
                .startAt(course.getStartAt())
                .endAt(course.getEndAt())
                .inProgress(course.isInProgress())
                .isFinished(course.isFinished())
                .createdAt(course.getCreatedAt())
                .build();
    }

    public Course toCourse() {
        return Course.builder()
                .id(this.id)
                .user(this.user)
                .title(this.title)
                .startAt(this.startAt)
                .endAt(this.endAt)
                .inProgress(this.inProgress)
                .isFinished(this.isFinished)
                .createdAt(this.createdAt)
                .build();
    }
}
