package kr.yeoksi.ours.oursserver.service;

import kr.yeoksi.ours.oursserver.controller.UserApiController;
import kr.yeoksi.ours.oursserver.domain.*;
import kr.yeoksi.ours.oursserver.exception.*;
import kr.yeoksi.ours.oursserver.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TermsOfServiceRepository termsOfServiceRepository;
    private final TermsAgreementRepository termsAgreementRepository;
    private final PlaceBookmarkRepository placeBookmarkRepository;
    private final PlaceFavoriteRepository placeFavoriteRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public String signUp(User user, List<TermsOfService> agreedTerms) {

        // 회원 중복 체크가 필요한가? -> 파이어베이스가 해주나?
        Optional<User> duplicatedUser = userRepository.checkDuplicatedUser(user.getId());
        if(duplicatedUser.isPresent()) {
            // 이미 존재하는 유저인 경우
            throw new DuplicatedUserException(ErrorCode.DUPLICATED_USER);
        }

        // 회원 저장
        userRepository.save(user);

        // 동의한 이용 약관 하나 하나에 맞게 저장
        for(TermsOfService terms : agreedTerms) {
            termsAgreementRepository.save(new TermAgreement(user, terms));
        }

        return user.getId();
    }

    /**
     * 회원가입 - 이메일이 이미 존재하는지 여부 확인
     */
    public void checkEmailExistence(String email) {

        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) throw new DuplicatedEmailException(ErrorCode.DUPLICATED_EMAIL);
    }

    /**
     * id로 유저 조회하기
     */
    public User findById(String id) {

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) return user.get();
        else throw new NotExistedUserException(ErrorCode.NOT_EXISTED_USER);
    }

    /**
     * 닉네임 중복 체크
     */
    public void checkNicknameExistence(String nickname) {

        Optional<User> user = userRepository.findByNickname(nickname);
        if(user.isPresent()) throw new DuplicatedNicknameException(ErrorCode.DUPLICATED_NICKNAME);
    }

    /**
     * 유저 정보 수정
     */
    @Transactional
    public void updateUserInformation(UserApiController.UpdateUserInformationRequest request) {

        User user = userRepository.findById(request.getUserIndex()).get();

        if(request.getNickname() != null) user.setNickname(request.getNickname());
        if(request.getPhoneNumber() != null) user.setPhoneNumber(request.getPhoneNumber());
        if(request.getGender() != null) user.setGender(request.getGender());
        if(request.getBirthday() != null) user.setBirthday(request.getBirthday());
    }

    /**
     * 이용약관 리스트 받기
     */
    public List<TermsOfService> readAllTerms() {

        return termsOfServiceRepository.readAllTerms();
    }

    /**
     * 공간에 북마크 누르기
     */
    @Transactional
    public void createPlaceBookmark(User user, Place place) {

        Optional<PlaceBookmark> placeBookmark = placeBookmarkRepository.findByIds(user.getId(), place.getId());
        if(placeBookmark.isPresent()) throw new DuplicatedPlaceBookmarkException(ErrorCode.DUPLICATED_PLACE_BOOKMARK);

        PlaceBookmark newPlaceBookmark = new PlaceBookmark(user, place);
        placeBookmarkRepository.save(newPlaceBookmark);
    }

    /**
     * 공간 북마크 삭제하기
     */
    @Transactional
    public void deletePlaceBookmark(User user, Place place) {

        Optional<PlaceBookmark> placeBookmark = placeBookmarkRepository.findByIds(user.getId(), place.getId());
        if(!placeBookmark.isPresent()) throw new NotExistedPlaceBookmarkException(ErrorCode.NOT_EXISTED_PLACE_BOOKMARK);

        placeBookmarkRepository.delete(placeBookmark.get());
    }

    /**
     * 유저가 북마크한 공간 리스트 조회하기
     */
    public List<PlaceBookmark> readAllPlaceBookmark(User user) {

        return placeBookmarkRepository.findAllBookmarkedPlace(user.getId());
    }

    /**
     * 공간에 좋아요 누르기
     */
    @Transactional
    public void createPlaceFavorite(User user, Place place) {

        Optional<PlaceFavorite> placeFavorite = placeFavoriteRepository.findByIds(user.getId(), place.getId());
        if(placeFavorite.isPresent()) throw new DuplicatedPlaceFavoriteException(ErrorCode.DUPLICATED_PLACE_FAVORITE);

        PlaceFavorite newPlaceFavorite = new PlaceFavorite(user, place);
        placeFavoriteRepository.save(newPlaceFavorite);
    }

    /**
     * 공간 좋아요 삭제하기
     */
    @Transactional
    public void deletePlaceFavorite(User user, Place place) {

        Optional<PlaceFavorite> placeFavorite = placeFavoriteRepository.findByIds(user.getId(), place.getId());
        if(!placeFavorite.isPresent()) throw new NotExistedPlaceFavoriteException(ErrorCode.NOT_EXISTED_PLACE_FAVORITE);

        placeFavoriteRepository.delete(placeFavorite.get());
    }
}
