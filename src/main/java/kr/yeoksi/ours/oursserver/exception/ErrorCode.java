package kr.yeoksi.ours.oursserver.exception;

import com.google.api.Http;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATED_USER(HttpStatus.CONFLICT, "이미 존재하는 사용자입니다."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "이미 사용중인 이메일입니다."),
    NOT_EXISTED_USER(HttpStatus.BAD_REQUEST, "존재하지 않는 유저입니다.");

    private HttpStatus status;
    private String message;
}
