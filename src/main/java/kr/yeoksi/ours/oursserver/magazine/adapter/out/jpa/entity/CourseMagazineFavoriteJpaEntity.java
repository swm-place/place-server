package kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.entity;


import jakarta.persistence.*;
import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;


@Entity(name = "course_magazine_favorite")
@Getter @Setter
@Builder @AllArgsConstructor
@NoArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_index", "course_magazine_index"}
                )
        }
)
public class CourseMagazineFavoriteJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_magazine_favorite_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "course_magazine_index")
    private CourseMagazineJpaEntity courseMagazine;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public CourseMagazineFavoriteJpaEntity(User user, CourseMagazineJpaEntity courseMagazine) {
        this.user = user;
        this.courseMagazine = courseMagazine;
    }

}
