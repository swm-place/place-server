package kr.yeoksi.ours.oursserver.magazine.adapter.in.response;

import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserInCourseMagazineResponse {
    private String id;
    private String nickname;
    private String imgUrl;

}
