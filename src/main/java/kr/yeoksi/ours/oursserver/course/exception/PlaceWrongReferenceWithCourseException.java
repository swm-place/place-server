package kr.yeoksi.ours.oursserver.course.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class PlaceWrongReferenceWithCourseException extends ServiceException {
    public PlaceWrongReferenceWithCourseException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 장소입니다.");
    }
}
