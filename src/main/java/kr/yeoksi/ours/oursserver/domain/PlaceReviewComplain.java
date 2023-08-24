package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"review_index", "user_index"}
                )
        }
)
public class PlaceReviewComplain {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_review_complain_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "review_index")
    private PlaceReview placeReview;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;
}
