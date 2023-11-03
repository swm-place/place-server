package kr.yeoksi.ours.oursserver.course.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedBookmarkException extends ServiceException {
    public DuplicatedBookmarkException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
