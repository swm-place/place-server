package kr.yeoksi.ours.oursserver.course.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedPlaceInCourseException extends ServiceException {
    public NotExistedPlaceInCourseException() {
        super(HttpStatus.NOT_FOUND, "해당 코스에 존재하지 않는 장소입니다.");
    }
}
