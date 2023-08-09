package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.yeoksi.ours.oursserver.domain.idclass.PlacesInGroupId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@IdClass(PlacesInGroupId.class)
public class PlacesInGroup {

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_index")
    private Place place;

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_group_index")
    private PlaceGroup placeGroup;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
