package kr.yeoksi.ours.oursserver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {

    private String resultCode;
    private T result;

    public static <T> Response<T> error(String resultCode) {
        return new Response("Error", resultCode);
    }

    public static <T> Response<T> success(T result) {
        return new Response("Success", result);
    }
}
