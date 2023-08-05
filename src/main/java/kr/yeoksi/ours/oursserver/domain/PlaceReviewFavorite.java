package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import kr.yeoksi.ours.oursserver.domain.idclass.UserReviewId;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@IdClass(UserReviewId.class)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"review_index", "user_index"}
                )
        }
)
public class PlaceReviewFavorite {

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "review_index")
    private PlaceReview placeReview;

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;
}
