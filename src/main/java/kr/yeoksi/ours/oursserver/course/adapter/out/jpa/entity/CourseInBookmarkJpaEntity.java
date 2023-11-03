package kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity(name = "course_in_bookmark")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CourseInBookmarkJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_in_bookmark_index")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_bookmark_index")
    private CourseBookmarkJpaEntity courseBookmark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_index")
    private CourseJpaEntity course;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
