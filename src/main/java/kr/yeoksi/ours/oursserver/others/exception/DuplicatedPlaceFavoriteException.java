package kr.yeoksi.ours.oursserver.others.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DuplicatedPlaceFavoriteException extends RuntimeException {

    private ErrorCode errorCode;
}
