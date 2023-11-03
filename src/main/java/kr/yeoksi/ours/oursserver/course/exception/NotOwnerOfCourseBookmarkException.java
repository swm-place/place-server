package kr.yeoksi.ours.oursserver.course.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotOwnerOfCourseBookmarkException extends ServiceException {
    public NotOwnerOfCourseBookmarkException() {
        super(HttpStatus.FORBIDDEN, "해당 북마크에 대한 권한이 없습니다.");
    }
}
