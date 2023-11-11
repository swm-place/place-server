package kr.yeoksi.ours.oursserver.others.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    // TODO: @JsonIgnore 대신 응답용 DTO 사용
    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_index")
    private Place place;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @NotBlank
    @Column(length = 255)
    @Size(max = 255)
    private String contents;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime cratedAt;
}
