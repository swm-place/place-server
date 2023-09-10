package kr.yeoksi.ours.oursserver.others.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class PlaceReview {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_index")
    private Place place;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @NotBlank
    @Column(length = 150)
    @Size(max = 150)
    private String contents;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime cratedAt;
}
