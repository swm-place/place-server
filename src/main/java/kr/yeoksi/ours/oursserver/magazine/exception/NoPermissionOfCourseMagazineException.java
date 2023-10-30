package kr.yeoksi.ours.oursserver.magazine.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NoPermissionOfCourseMagazineException extends ServiceException {
    public NoPermissionOfCourseMagazineException() {
        super(HttpStatus.FORBIDDEN, "해당 코스 매거진에 대한 권한이 없습니다.");
    }
}
