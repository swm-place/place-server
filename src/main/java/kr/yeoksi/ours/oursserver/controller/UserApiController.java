package kr.yeoksi.ours.oursserver.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.yeoksi.ours.oursserver.domain.Response;
import kr.yeoksi.ours.oursserver.domain.TermsOfService;
import kr.yeoksi.ours.oursserver.domain.User;
import kr.yeoksi.ours.oursserver.service.TermService;
import kr.yeoksi.ours.oursserver.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final TermService termService;

    /**
     * 회원 가입
     */
    @PostMapping("/user")
    public ResponseEntity<Response<Void>> createUser(@RequestHeader("X-User-Uid") String uid,
                                                     @RequestHeader("X-User-Email") String email,
                                                     @RequestBody @Valid CreateUserRequest request) {

        User user = new User();
        user.setId(uid);
        user.setEmail(email);

        // 닉네임 형식 체크 로직 필요.
        user.setNickname(request.getNickname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setGender(request.getGender());
        user.setBirthday(request.getBirthday());

        // 동의한 약관 리스트
        // 필수 약관에 대해 동의하지 않는 경우에 대한 예외 처리는 프론트에서 해준다고 가정하고 진행.
        List<Long> agreedTermsIndex = request.getTermIndex();
        List<TermsOfService> agreedTerms = termService.getAgreedTerms(agreedTermsIndex);

        userService.signUp(user, agreedTerms);

        return ResponseEntity.ok()
                .body(Response.success(null));
    }

    /**
     * 회원 가입 - 이미 존재하는 이메일인지 확인
     */
    @GetMapping("/user/email")
    public ResponseEntity<Response<Void>> checkEmailExistence(@RequestParam @Valid String email) {

        if(email.isEmpty()) throw new RuntimeException();
        userService.checkEmailExistence(email);

        return ResponseEntity.ok()
                .body(Response.success(null));
    }

    /**
     * 유저 정보 조회
     */
    @GetMapping("/user/{userIndex}")
    public ResponseEntity<Response<UserResponse>> getUserInformation(
            @PathVariable("userIndex") String id) {

        User user = userService.findById(id);

        return ResponseEntity.ok().body(
                Response.success(
                        new UserResponse(
                                user.getEmail(),
                                user.getNickname(),
                                user.getPhoneNumber(),
                                user.getGender(),
                                user.getBirthday(),
                                user.getCreatedAt(),
                                user.getLastLoginAt())));
    }

    /**
     * 닉네임 중복 체크
     */
    @GetMapping("/user/nickname")
    public ResponseEntity<Response<Void>> checkNicknameExistence(@RequestParam @Valid String nickname) {

        userService.checkNicknameExistence(nickname);

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

    /**
     * 유저 정보 조회하기에 대한 응답을 위한 DTO
     */
    @Data
    @AllArgsConstructor
    static class UserResponse {

        private String email;
        private String nickname;
        private String phoneNumber;
        private Integer gender;
        private String birthday;
        private LocalDateTime createdAt;
        private LocalDateTime lastLoginAt;
    }
}
