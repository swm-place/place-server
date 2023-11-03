package kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity;

import jakarta.persistence.*;
import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;


@Entity(name = "course_bookmark")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CourseBookmarkJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_bookmark_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @Column(name = "title")
    private String title;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "courseBookmark", cascade = CascadeType.ALL)
    private List<CourseInBookmarkJpaEntity> coursesInBookmark = new ArrayList<>();

}
