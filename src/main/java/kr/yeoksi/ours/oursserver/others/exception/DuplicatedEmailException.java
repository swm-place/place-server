package kr.yeoksi.ours.oursserver.others.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DuplicatedEmailException extends RuntimeException {

    private ErrorCode errorCode;
}
