package kr.yeoksi.ours.oursserver.magazine.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class PlaceWrongReferenceException extends ServiceException {
    public PlaceWrongReferenceException() {
        super(HttpStatus.BAD_REQUEST, "등록되지 않은 장소가 있습니다.");
    }
}
