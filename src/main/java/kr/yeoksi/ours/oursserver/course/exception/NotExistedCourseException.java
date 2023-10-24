package kr.yeoksi.ours.oursserver.course.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;


public class NotExistedCourseException extends ServiceException {

    public NotExistedCourseException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 코스입니다.");
    }

}
