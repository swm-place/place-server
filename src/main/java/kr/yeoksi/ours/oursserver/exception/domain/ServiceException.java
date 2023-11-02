package kr.yeoksi.ours.oursserver.exception.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
@RequiredArgsConstructor
public class ServiceException extends RuntimeException {

    final HttpStatus httpStatus;
    final String message;

}
