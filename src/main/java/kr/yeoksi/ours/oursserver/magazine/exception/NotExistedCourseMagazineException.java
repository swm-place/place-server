package kr.yeoksi.ours.oursserver.magazine.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedCourseMagazineException extends ServiceException {
    public NotExistedCourseMagazineException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 코스 매거진입니다.");
    }
}
