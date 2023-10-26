package kr.yeoksi.ours.oursserver.others.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"place_index", "user_index"}
                )
        }
)
public class PlaceFavorite {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_favorite_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_index")
    private Place place;

    // TODO: @JsonIgnore 대신 응답용 DTO 사용
    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    public PlaceFavorite(User user, Place place) {
        this.user = user;
        this.place = place;
    }
}
