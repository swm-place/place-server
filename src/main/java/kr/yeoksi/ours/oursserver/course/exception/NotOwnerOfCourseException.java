package kr.yeoksi.ours.oursserver.course.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotOwnerOfCourseException extends ServiceException {
    public NotOwnerOfCourseException() {
        super(HttpStatus.FORBIDDEN, "해당 코스에 대한 권한이 없습니다.");
    }
}
