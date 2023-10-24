package kr.yeoksi.ours.oursserver.exception;

import kr.yeoksi.ours.oursserver.exception.domain.ServiceException;
import kr.yeoksi.ours.oursserver.others.domain.Response;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice @Order(1)
public class GlobalServiceExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<?> handleServiceException(ServiceException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(Response.error(e.getMessage()));
    }

}
