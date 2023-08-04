package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import kr.yeoksi.ours.oursserver.domain.idclass.PlaceFavoriteId;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@IdClass(PlaceFavoriteId.class)
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
}
