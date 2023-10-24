package kr.yeoksi.ours.oursserver.others.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DuplicatedPlaceInBookmarkException extends RuntimeException {

    private ErrorCode errorCode;
}
