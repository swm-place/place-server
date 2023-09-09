package kr.yeoksi.ours.oursserver.others.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"place_index", "place_bookmark_index"}
                )
        }
)
public class PlaceInBookmark {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "places_in_bookmark_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_index")
    private Place place;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_bookmark_index")
    private PlaceBookmark placeBookmark;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
