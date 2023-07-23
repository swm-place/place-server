package kr.yeoksi.ours.oursserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotExistedUserException extends RuntimeException {

    private ErrorCode errorCode;
}
