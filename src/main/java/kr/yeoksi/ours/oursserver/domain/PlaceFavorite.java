package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import kr.yeoksi.ours.oursserver.domain.idclass.UserPlaceId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@IdClass(UserPlaceId.class)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"place_index", "user_index"}
                )
        }
)
public class PlaceFavorite {

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_index")
    private Place place;

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    public PlaceFavorite(User user, Place place) {
        this.user = user;
        this.place = place;
    }
}
