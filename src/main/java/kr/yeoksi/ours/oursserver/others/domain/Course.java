package kr.yeoksi.ours.oursserver.others.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Course {

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
}
