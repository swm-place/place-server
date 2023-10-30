package kr.yeoksi.ours.oursserver.magazine.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedCourseMagazineException extends ServiceException {
    public DuplicatedCourseMagazineException() {
        super(HttpStatus.BAD_REQUEST, "이미 존재하는 코스 매거진입니다.");
    }
}
