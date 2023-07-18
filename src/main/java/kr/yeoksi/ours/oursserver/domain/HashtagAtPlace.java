package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import kr.yeoksi.ours.oursserver.domain.idclass.HashtagAtPlaceId;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@IdClass(HashtagAtPlaceId.class)
public class HashtagAtPlace {

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hashtag_index")
    private Hashtag hashtag;

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_index")
    private Place place;
}
