package kr.yeoksi.ours.oursserver.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.yeoksi.ours.oursserver.domain.*;
import kr.yeoksi.ours.oursserver.service.PlaceService;
import kr.yeoksi.ours.oursserver.service.TermService;
import kr.yeoksi.ours.oursserver.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final TermService termService;
    private final PlaceService placeService;

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
     * 유저 정보 수정
     */
    @PatchMapping("/user")
    public ResponseEntity<Response<Void>> updateUserInformation(@RequestBody @Valid UpdateUserInformationRequest request) {

        userService.updateUserInformation(request);

        return ResponseEntity.ok()
                .body(Response.success(null));
    }

    /**
     * 이용약관 리스트 받기
     */
    @GetMapping("user/terms")
    public ResponseEntity<Response<List<TermResponse>>> readAllTerms() {

        List<TermsOfService> terms = userService.readAllTerms();
        List<TermResponse> result = new ArrayList<>();
        for(TermsOfService term : terms) {
            TermResponse response = new TermResponse(
                    term.getId(),
                    term.getTitle(),
                    term.getContents(),
                    term.getType(),
                    term.getVersion(),
                    term.isRequired(),
                    term.getCreatedAt()
            );

            result.add(response);
        }


        return ResponseEntity.ok().body(
                Response.success(
                        result
                )
        );
    }

    /**
     * 공간 북마크 그룹 생성하기
     */
    @PostMapping("/user/{userIndex}/place-bookmark")
    public ResponseEntity<Response<CreatePlaceBookmarkResponse>> createPlaceBookmark(
            @PathVariable("userIndex") @NotBlank String userId,
            @RequestBody @Valid CreatePlaceBookmarkRequest request) {

        User user = userService.findById(userId);

        PlaceBookmark placeBookmark = new PlaceBookmark();
        placeBookmark.setUser(user);
        placeBookmark.setTitle(request.getTitle());
        Long savedBookmarkId = userService.createPlaceBookmark(placeBookmark);

        return ResponseEntity.ok().body(
                Response.success(
                        new CreatePlaceBookmarkResponse(
                                savedBookmarkId
                        )
                )
        );
    }

    /**
     * 유저의 공간 북마크 그룹 리스트 조회하기
     */
    @GetMapping("/user/{userIndex}/place-bookmark")
    public ResponseEntity<Response<List<ReadPlaceBookmarkResponse>>> readPlaceBookmark(
            @PathVariable("userIndex") @NotBlank String userId) {

        User user = userService.findById(userId);

        List<PlaceBookmark> placeBookmarkList = user.getPlaceBookmarks();
        List<ReadPlaceBookmarkResponse> readPlaceBookmarkResponse
                = placeBookmarkList.stream()
                .map(
                        b -> new ReadPlaceBookmarkResponse(
                                b.getId(),
                                b.getTitle()))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(
                Response.success(
                        readPlaceBookmarkResponse
                )
        );
    }

    /**
     * 공간 북마크하기.
     */
    /*
    @PostMapping("/user/{userIndex}/place-bookmark/{placeBookmarkIndex}")
    public ResponseEntity<Response<PlaceBookmarkResponse>> createPlaceBookmark2(
            @PathVariable("userIndex") @NotBlank String userId,
            @RequestBody @Valid CreatePlaceBookmarkRequest2 request) {

        User user = userService.findById(userId);
        Place place = placeService.findById(request.getPlaceId());
        //userService.createPlaceBookmark(user,place);

        boolean isBookmark = placeService.checkBookmark(userId, request.getPlaceId());

        return ResponseEntity.ok().body(
                Response.success(
                        new PlaceBookmarkResponse(
                                isBookmark
                        )
                )
        );
    }
     */

    /**
     * 공간 북마크 삭제하기.
     */
    /*
    @DeleteMapping("/user/{userIndex}/place-bookmark/{placeIndex}")
    public ResponseEntity<Response<PlaceBookmarkResponse>> deletePlaceBookmark(
            @PathVariable("userIndex") @NotBlank String userId,
            @PathVariable("placeIndex") @NotNull Long placeId) {

        User user = userService.findById(userId);
        Place place = placeService.findById(placeId);
        userService.deletePlaceBookmark(user, place);

        boolean isBookmark = placeService.checkBookmark(userId, placeId);

        return ResponseEntity.ok().body(
                Response.success(
                        new PlaceBookmarkResponse(
                                isBookmark
                        )
                )
        );
    }

     */

    /**
     * 유저가 북마크한 공간 조회하기.
     */
    /*
    @GetMapping("/user/{userIndex}/place-bookmark")
    public ResponseEntity<Response<List<ReadPlaceBookmarkResponse>>> readPlaceBookmarks(
            @PathVariable("userIndex") @NotBlank String userId) {

        User user = userService.findById(userId);
        List<PlaceBookmark> bookmarkedPlaces = userService.readAllPlaceBookmark(user);

        return ResponseEntity.ok().body(
                Response.success(
                        bookmarkedPlaces.stream().map(
                                b -> new ReadPlaceBookmarkResponse(
                                        b.getPlace().getId(),
                                        b.getPlace().getName()))
                                .collect(Collectors.toList())));
    }
     */

    /**
     * 공간 좋아요 누르기.
     */
    @PostMapping("/user/{userIndex}/place-favorite")
    public ResponseEntity<Response<PlaceFavoriteResponse>> createPlaceFavorite(
            @PathVariable("userIndex") @NotBlank String userId,
            @RequestBody @Valid CreatePlaceFavoriteRequest request) {

        User user = userService.findById(userId);
        Place place = placeService.findById(request.getPlaceId());
        userService.createPlaceFavorite(user, place);

        boolean isFavorite = placeService.checkFavorite(userId, request.getPlaceId());

        return ResponseEntity.ok().body(
                Response.success(
                        new PlaceFavoriteResponse(
                                isFavorite
                        )
                )
        );
    }

    /**
     * 공간 좋아요 삭제하기.
     */
    @DeleteMapping("/user/{userIndex}/place-favorite/{placeIndex}")
    public ResponseEntity<Response<PlaceFavoriteResponse>> deletePlaceFavorite(
            @PathVariable("userIndex") @NotBlank String userId,
            @PathVariable("placeIndex") @NotNull Long placeId) {

        User user = userService.findById(userId);
        Place place = placeService.findById(placeId);
        userService.deletePlaceFavorite(user, place);

        boolean isFavorite = placeService.checkFavorite(userId, placeId);

        return ResponseEntity.ok().body(
                Response.success(
                        new PlaceFavoriteResponse(
                                isFavorite
                        )
                )
        );
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
        private LocalDateTime birthday;

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
        private LocalDateTime birthday;
        private LocalDateTime createdAt;
        private LocalDateTime lastLoginAt;
    }

    /**
     * 유저 정보 수정에 필요한 정보를 받아오기 위한 DTO
     */
    @Data
    @AllArgsConstructor
    public static class UpdateUserInformationRequest {

        @NotBlank
        private String userIndex;

        private String nickname;
        private String phoneNumber;
        private Integer gender;
        private LocalDateTime birthday;
    }

    /**
     * 이용약관 리스트 조회의 응답을 위한 DTO
     */
    @Data
    @AllArgsConstructor
    static class TermResponse {

        private Long id;
        private String title;
        private String contents;
        private String type;
        private Integer version;
        private boolean required;
        private LocalDateTime createdAt;
    }

    /**
     * 공간 북마크 그룹 생성하기의 요청을 위한 DTO
     */
    @Data
    static class CreatePlaceBookmarkRequest {

        @NotBlank
        private String title;
    }

    /**
     * 공간 북마크하기/삭제의 응답을 위한 DTO
     */
    @Data
    @AllArgsConstructor
    static class PlaceBookmarkResponse {

        private boolean isBookmark;
    }

    /**
     * 유저의 북마크 그룹 리스트 조회하기의 응답을 위한 DTO
     */
    @Data
    @AllArgsConstructor
    static class ReadPlaceBookmarkResponse {

        private Long placeBookmarkId;
        private String title;
    }

    /**
     * 공간 좋아요 누르기/삭제하기의 응답을 위한 DTO
     */
    @Data
    @AllArgsConstructor
    static class PlaceFavoriteResponse {

        private boolean isFavorite;
    }

    /**
     * 공간 좋아요 누르기의 요청을 위한 DTO
     */
    @Data
    static class CreatePlaceFavoriteRequest {

        @NotNull
        private Long placeId;
    }

    /**
     * 공간 북마크 그룹 생성하기의 응답을 위한 DTO
     */
    @Data
    @AllArgsConstructor
    static class CreatePlaceBookmarkResponse {

        private Long placeBookmarkId;
    }
}
