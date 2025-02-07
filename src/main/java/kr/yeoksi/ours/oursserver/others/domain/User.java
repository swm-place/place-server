package kr.yeoksi.ours.oursserver.others.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kr.yeoksi.ours.oursserver.course.adapter.out.jpa.entity.CourseJpaEntity;
import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.entity.CourseMagazineJpaEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "users")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "user_index")
    private String id;

    @NotBlank
    @Column(unique = true, length = 25)
    @Size(max = 25)
    private String email;

    @NotBlank
    @Column(unique = true, length = 20)
    @Size(min = 3, max = 20)
    private String nickname;

    @NotBlank
    @Column(name = "phone_number", length = 15)
    @Size(max = 15)
    private String phoneNumber;

    @NotNull
    @ColumnDefault("true")
    @Column(columnDefinition = "TINYINT(1)")
    private int gender;

    @NotNull
    private LocalDateTime birthday;

    @Column(name = "img_url")
    private String imgUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @OneToMany(mappedBy = "user")
    private List<CourseJpaEntity> courses;

    @OneToMany(mappedBy = "user")
    private List<PlaceFavorite> placeFavorites;

    @OneToMany(mappedBy = "user")
    private List<PlaceBookmark> placeBookmarks;

    @OneToMany(mappedBy = "user")
    private List<PlaceOpen> placeOpens;

    @OneToMany(mappedBy = "user")
    private List<CourseMagazineJpaEntity> courseMagazines;
}
