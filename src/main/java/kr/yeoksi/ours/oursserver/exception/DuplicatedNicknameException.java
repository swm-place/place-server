package kr.yeoksi.ours.oursserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DuplicatedNicknameException extends RuntimeException {

    private ErrorCode errorCode;
}
