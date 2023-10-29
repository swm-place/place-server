package kr.yeoksi.ours.oursserver.magazine.adapter.in.response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PlaceWithCourseMagazineResponse {

        private Long id;

        private String name;
        private String category;
        private String openTime;

}
