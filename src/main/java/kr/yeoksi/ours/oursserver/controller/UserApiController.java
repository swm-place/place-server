package kr.yeoksi.ours.oursserver.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.yeoksi.ours.oursserver.domain.Response;
import kr.yeoksi.ours.oursserver.domain.TermsOfService;
import kr.yeoksi.ours.oursserver.domain.User;
import kr.yeoksi.ours.oursserver.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    /**
     * 회원 가입
     */
    @PostMapping("/sign-up")
    public ResponseEntity<Response<Void>> createUser(@RequestHeader("X-User-Uid") String uid,
                                                     @RequestHeader("X-User-Email") String email,
                                                     @RequestBody @Valid CreateUserRequest request) {

        User user = new User();
        user.setId(uid);
        user.setEmail(email);
        user.setNickname(request.getNickname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setGender(request.getGender());
        user.setBirthday(request.getBirthday());

        // 동의한 약관 리스트
        // 필수 약관에 대해 동의하지 않는 경우에 대한 예외 처리는 프론트에서 해준다고 가정하고 진행.
        List<Long> agreedTermsIndex = request.getTermIndex();
        List<TermsOfService> agreedTerms = userService.getAgreedTerms(agreedTermsIndex);
        user.setTerms(agreedTerms);

        userService.signUp(user);

        return ResponseEntity.ok()
                .body(Response.success(null));
    }

    /**
     * 회원가입에 필요한 정보를 받아오기 위한 DTO
     */
    @Data
    static class CreateUserRequest {
        @NotBlank
        private String nickname;

        @NotBlank
        private String phoneNumber;

        @NotNull
        private Integer gender;

        @NotBlank
        private String birthday;

        private List<Long> termIndex;
    }
}
