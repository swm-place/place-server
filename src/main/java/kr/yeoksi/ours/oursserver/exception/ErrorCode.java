package kr.yeoksi.ours.oursserver.exception;

import com.google.api.Http;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATED_USER(HttpStatus.CONFLICT, "이미 존재하는 사용자입니다."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "이미 사용중인 이메일입니다."),
    NOT_EXISTED_USER(HttpStatus.BAD_REQUEST, "존재하지 않는 유저입니다."),
    DUPLICATED_NICKNAME(HttpStatus.CONFLICT, "이미 사용중인 닉네입입니다."),
    NOT_EXISTED_PLACE(HttpStatus.BAD_REQUEST, "존재하지 않는 공간입니다."),
    DUPLICATED_PLACE_BOOKMARK(HttpStatus.CONFLICT, "이미 존재하는 공간 북마크입니다."),
    NOT_EXISTED_PLACE_BOOKMARK(HttpStatus.BAD_REQUEST, "존재하지 않는 공간 북마크 그룹입니다."),
    DUPLICATED_PLACE_FAVORITE(HttpStatus.CONFLICT, "이미 존재하는 공간 좋아요입니다."),
    NOT_EXISTED_PLACE_FAVORITE(HttpStatus.BAD_REQUEST, "존재하지 않는 공간 좋아요입니다."),
    DUPLICATED_PLACE_IN_BOOKMARK(HttpStatus.CONFLICT, "이미 북마크 내에 해당 공간이 존재합니다."),
    NOT_FOUND_PLACE_AT_ELASTIC_SEARCH(HttpStatus.BAD_REQUEST, "엘라스틱에서 해당 공간을 찾지 못했습니다."),
    INSUFFICIENT_PRIVILEGES(HttpStatus.FORBIDDEN, "권한이 없는 정보입니다."),
    NOT_EXISTED_PLACE_IN_BOOKMARK(HttpStatus.BAD_REQUEST, "북마크 내에 해당 공간이 존재하지 않습니다.");

    private HttpStatus status;
    private String message;
}
