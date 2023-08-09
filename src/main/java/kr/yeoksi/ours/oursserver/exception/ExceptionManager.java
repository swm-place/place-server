package kr.yeoksi.ours.oursserver.exception;

import kr.yeoksi.ours.oursserver.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(e.getMessage()));
    }

    @ExceptionHandler(DuplicatedUserException.class)
    public ResponseEntity<?> duplicatedUserException(DuplicatedUserException e) {

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(DuplicatedEmailException.class)
    public ResponseEntity<?> duplicatedEmailException(DuplicatedEmailException e) {

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(NotExistedUserException.class)
    public ResponseEntity<?> notExistedUserException(NotExistedUserException e) {

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(DuplicatedNicknameException.class)
    public ResponseEntity<?> duplicatedNikcnameException(DuplicatedNicknameException e) {

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(NotExistedPlaceException.class)
    public ResponseEntity<?> notExistedPlaceException(NotExistedPlaceException e) {

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(DuplicatedPlaceBookmarkException.class)
    public ResponseEntity<?> duplicatedPlaceBookmarkException(DuplicatedPlaceBookmarkException e) {

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(NotExistedPlaceBookmarkException.class)
    public ResponseEntity<?> notExistedPlaceBookmarkException(NotExistedPlaceBookmarkException e) {

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }
}
