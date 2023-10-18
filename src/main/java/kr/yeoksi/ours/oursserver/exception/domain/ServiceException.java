package kr.yeoksi.ours.oursserver.exception.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class ServiceException extends RuntimeException {

    final HttpStatus httpStatus;
    final String message;

}
