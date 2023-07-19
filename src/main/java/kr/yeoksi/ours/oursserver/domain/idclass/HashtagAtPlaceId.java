package kr.yeoksi.ours.oursserver.domain.idclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HashtagAtPlaceId implements Serializable {

    private Long hashtag;
    private Long place;
}
