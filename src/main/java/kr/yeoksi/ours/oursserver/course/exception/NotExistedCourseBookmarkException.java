package kr.yeoksi.ours.oursserver.course.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedCourseBookmarkException extends ServiceException {

    public NotExistedCourseBookmarkException() {
        super(HttpStatus.NOT_FOUND, "해당 북마크가 존재하지 않습니다.");
    }

}
