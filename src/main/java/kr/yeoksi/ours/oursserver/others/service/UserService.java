package kr.yeoksi.ours.oursserver.others.service;

import kr.yeoksi.ours.oursserver.others.controller.UserApiController;
import kr.yeoksi.ours.oursserver.others.domain.dto.place.response.ThumbnailInfoResponse;
import kr.yeoksi.ours.oursserver.others.exception.*;
import kr.yeoksi.ours.oursserver.others.domain.*;
import kr.yeoksi.ours.oursserver.others.dto.UpdateUserInformationResponse;
import kr.yeoksi.ours.oursserver.others.repository.*;
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
    private final PlaceInBookmarkRepository placeInBookmarkRepository;
    private final PlaceOpenRepository placeOpenRepository;
    private final PlaceRepository placeRepository;

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
    public UpdateUserInformationResponse updateUserInformation(String uid,
                                                               UserApiController.UpdateUserInformationRequest request) {

        Optional<User> findUser = userRepository.findById(request.getUserIndex());

        // 존재하지 않는 유저에 대한 수정
        if(!findUser.isPresent()) throw new NotExistedUserException(ErrorCode.NOT_EXISTED_USER);

        User user = findUser.get();

        // 다른 유저의 정보 수정 시도
        if(!user.getId().equals(uid)) throw new InsufficientPrivilegesException(ErrorCode.INSUFFICIENT_PRIVILEGES);


        if(request.getNickname() != null) user.setNickname(request.getNickname());
        if(request.getPhoneNumber() != null) user.setPhoneNumber(request.getPhoneNumber());
        if(request.getGender() != null) user.setGender(request.getGender());
        if(request.getBirthday() != null) user.setBirthday(request.getBirthday());

        // 프로필 이미지 업로드에 대한 작업 필요.

        return new UpdateUserInformationResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getPhoneNumber(),
                user.getGender(),
                user.getBirthday(),
                user.getImgUrl()
        );
    }

    /**
     * 본인의 리소스에 대한 접근인지 확인하기
     */
    public void authentication(String headerUid, String inputUid) {

        if(!inputUid.equals(headerUid)) throw new InsufficientPrivilegesException(ErrorCode.INSUFFICIENT_PRIVILEGES);
    }

    /**
     * 이용약관 리스트 받기
     */
    public List<TermsOfService> readAllTerms() {

        return termsOfServiceRepository.readAllTerms();
    }

    /**
     * 공간 북마크 그룹 생성하기.
     */
    @Transactional
    public Long createPlaceBookmark(PlaceBookmark placeBookmark) {

        placeBookmarkRepository.save(placeBookmark);
        return placeBookmark.getId();
    }

    /**
     * 공간 북마크 그룹 조회하기
     */
    public PlaceBookmark getPlaceBookmark(Long placeBookmarkId) {

        Optional<PlaceBookmark> placeBookmark = placeBookmarkRepository.findById(placeBookmarkId);
        if(!placeBookmark.isPresent()) throw new NotExistedPlaceBookmarkException(ErrorCode.NOT_EXISTED_PLACE_BOOKMARK);

        return placeBookmark.get();
    }

    /**
     * 공간 북마크 그룹 삭제하기
     */
    @Transactional
    public void deletePlaceBookmark(PlaceBookmark placeBookmark) {

        placeBookmarkRepository.delete(placeBookmark);
    }

    /**
     * 공간 북마크하기
     */
    @Transactional
    public Long createPlaceInBookmark(PlaceInBookmark placeInBookmark) {

        Optional<PlaceInBookmark> checkPlaceInBookmark = placeInBookmarkRepository.findByIds(placeInBookmark.getPlace().getId(), placeInBookmark.getPlaceBookmark().getId());
        if(checkPlaceInBookmark.isPresent()) throw new DuplicatedPlaceInBookmarkException(ErrorCode.DUPLICATED_PLACE_IN_BOOKMARK);

        placeInBookmarkRepository.save(placeInBookmark);
        return placeInBookmark.getId();
    }

    /**
     * 장소 북마크 삭제하기
     */
    @Transactional
    public void deletePlaceInBookmark(String placeId, Long placeBookmarkId) {

        Optional<PlaceInBookmark> placeInBookmark = placeInBookmarkRepository.findByIds(placeId, placeBookmarkId);

        if (!placeInBookmark.isPresent())
            throw new NotExistedPlaceInBookmarkException(ErrorCode.NOT_EXISTED_PLACE_IN_BOOKMARK);

        placeInBookmarkRepository.delete(placeInBookmark.get());
    }

    /**
     * 유저의 장소 북마크 그룹 내의 장소들 조회하기.
     */
    public List<Place> readAllPlaceInBookmark(User user, PlaceBookmark placeBookmark) {

        return placeRepository.readAllPlaceInPlaceBookmark(user.getId(), placeBookmark.getId());
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

    /**
     * 유저가 장소를 북마크했는지 여부를 확인하기.
     */
    public boolean checkBookmark(String userId, String placeId) {

        Optional<PlaceInBookmark> placeInBookmark = placeInBookmarkRepository.checkBookmark(userId, placeId);
        if(!placeInBookmark.isPresent()) return false;
        else return true;
    }

    /**
     * 해당 장소에 대한 유저의 좋아요 여부 확인하기.
     */
    public boolean checkFavorite(String userId, String placeId) {

        Optional<PlaceFavorite> placeFavorite = placeFavoriteRepository.findByIds(userId, placeId);
        if(!placeFavorite.isPresent()) return false;
        else return true;
    }

    /**
     * 해당 장소에 대한 유저의 운영중 응답 여부 확인하기.
     */
    public boolean checkOpen(String userId, String placeId) {

        Optional<PlaceOpen> placeOpen = placeOpenRepository.findByIds(userId, placeId);
        if(!placeOpen.isPresent()) return false;
        else return true;
    }

    /**
     * 유저의 공간 북마크 그룹 리스트 조회하기.
     */
    public List<PlaceBookmark> readAllMyPlaceBookmark(String userId, int page, int size) {

        return placeBookmarkRepository.findByUserId(userId, page, size);
    }

    /**
     * 북마크 그룹의 썸네일 이미지, 장소 이름 조회
     */
    public List<ThumbnailInfoResponse> getThumbnailInfo(Long placeBookmarkId) {

        List<ThumbnailInfoResponse> thumbnailInfoResponseList = new ArrayList<>();

        List<PlaceInBookmark> placeList = placeBookmarkRepository.getThumbnailInfo(placeBookmarkId);
        if(!CollectionUtils.isEmpty(placeList)) {
            for(PlaceInBookmark place : placeList) {
                thumbnailInfoResponseList.add(
                        new ThumbnailInfoResponse(
                                place.getPlace().getName(),
                                place.getPlace().getImgUrl()
                        )
                );
            }
        }

        return thumbnailInfoResponseList;
    }
}
