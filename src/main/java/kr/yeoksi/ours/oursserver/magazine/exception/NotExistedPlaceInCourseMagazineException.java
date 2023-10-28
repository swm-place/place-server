package kr.yeoksi.ours.oursserver.magazine.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedPlaceInCourseMagazineException extends ServiceException {
    public NotExistedPlaceInCourseMagazineException() {
        super(HttpStatus.NOT_FOUND, "매거진 내 장소 아이템 정보를 찾을 수 없습니다.");
    }
}
