package kr.yeoksi.ours.oursserver.magazine.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NoPermissionOfFavoriteException extends ServiceException {
    public NoPermissionOfFavoriteException() {
        super(HttpStatus.FORBIDDEN, "권한이 없습니다.");
    }
}
