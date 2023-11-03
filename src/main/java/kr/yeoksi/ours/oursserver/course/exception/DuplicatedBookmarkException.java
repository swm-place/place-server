package kr.yeoksi.ours.oursserver.course.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedBookmarkException extends ServiceException {
    public DuplicatedBookmarkException() {
        super(HttpStatus.BAD_REQUEST, "이미 해당 북마크에 추가된 코스입니다.");
    }
}
