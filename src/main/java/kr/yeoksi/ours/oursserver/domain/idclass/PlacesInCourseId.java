package kr.yeoksi.ours.oursserver.domain.idclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlacesInCourseId implements Serializable {

    private Long course;
    private Long place;
}
