package kr.yeoksi.ours.oursserver.others.exception;

import kr.yeoksi.ours.oursserver.others.domain.Response;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice @Order(2)
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

    @ExceptionHandler(DuplicatedPlaceFavoriteException.class)
    public ResponseEntity<?> duplicatedPlaceFavoriteException(DuplicatedPlaceFavoriteException e) {

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(NotExistedPlaceFavoriteException.class)
    public ResponseEntity<?> notExistedPlaceFavoriteExceptions(NotExistedPlaceFavoriteException e) {

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(DuplicatedPlaceInBookmarkException.class)
    public ResponseEntity<?> duplicatedPlaceInBookmarkException(DuplicatedPlaceInBookmarkException e) {

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(NotFoundPlaceAtElasticSearchException.class)
    public ResponseEntity<?> notFoundPlaceAtElasticSearchException(NotFoundPlaceAtElasticSearchException e) {

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(InsufficientPrivilegesException.class)
    public ResponseEntity<?> insufficientPrivilegesException(InsufficientPrivilegesException e) {

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(NotExistedPlaceInBookmarkException.class)
    public ResponseEntity<?> notExistedPlaceInBookmarkException(NotExistedPlaceInBookmarkException e) {

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }
}
