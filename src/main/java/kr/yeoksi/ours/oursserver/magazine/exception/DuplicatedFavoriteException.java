package kr.yeoksi.ours.oursserver.magazine.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedFavoriteException extends ServiceException {

    public DuplicatedFavoriteException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
