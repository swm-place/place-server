package kr.yeoksi.ours.oursserver.others.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UpdateUserInformationResponse {

    private String id;
    private String email;
    private String nickname;
    private String phoneNumber;
    private int gender;
    private LocalDateTime birthday;
    private String imgUrl;
}
