package kr.yeoksi.ours.oursserver.course.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedCourseException extends ServiceException {
    public DuplicatedCourseException() {
        super(HttpStatus.BAD_REQUEST, "해당 ID를 가진 코스가 이미 존재합니다.");
    }
}
