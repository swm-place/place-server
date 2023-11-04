package kr.yeoksi.ours.oursserver.course.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;


public class NoPermissionOfBookmarkException extends ServiceException {
    public NoPermissionOfBookmarkException() {
        super(HttpStatus.FORBIDDEN, "권한이 없습니다.");
    }
}
