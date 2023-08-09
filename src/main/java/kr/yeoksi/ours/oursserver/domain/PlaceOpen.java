package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.yeoksi.ours.oursserver.domain.idclass.UserPlaceId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@IdClass(UserPlaceId.class)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"place_index", "user_index"}
                )
        }
)
public class PlaceOpen {

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_index")
    private Place place;

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
