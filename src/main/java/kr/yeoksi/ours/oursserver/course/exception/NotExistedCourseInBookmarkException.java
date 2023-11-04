package kr.yeoksi.ours.oursserver.course.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedCourseInBookmarkException extends ServiceException {
    public NotExistedCourseInBookmarkException() {
        super(HttpStatus.BAD_REQUEST, "해당 북마크에 추가되지 않은 코스입니다.");
    }
}
