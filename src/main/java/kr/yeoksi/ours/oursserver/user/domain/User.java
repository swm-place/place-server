package kr.yeoksi.ours.oursserver.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;
    private String nickname;

    private String email;
    private String phoneNumber;

    private Integer gender;
    private LocalDateTime birthday;

    private String imgUrl;

    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;

}
