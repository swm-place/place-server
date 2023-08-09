package kr.yeoksi.ours.oursserver.service;

import kr.yeoksi.ours.oursserver.controller.UserApiController;
import kr.yeoksi.ours.oursserver.domain.*;
import kr.yeoksi.ours.oursserver.exception.*;
import kr.yeoksi.ours.oursserver.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;
    @Autowired PlaceService placeService;

    @Autowired UserRepository userRepository;
    @Autowired
    TermsOfServiceRepository termsOfServiceRepository;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    PlaceBookmarkRepository placeBookmarkRepository;
    @Autowired
    PlaceFavoriteRepository placeFavoriteRepository;

    @Test
    public void 회원가입() throws Exception {

        // given
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());

        List<TermsOfService> agreedTerms = new ArrayList<>();

        TermsOfService termA = new TermsOfService();
        termA.setTitle("테스트 타이틀");
        termA.setContents("테스트 내용");
        termA.setType("테스트 타입");
        termA.setVersion(1);

        termsOfServiceRepository.save(termA);

        agreedTerms.add(termA);


        // when
        String savedUserId = userService.signUp(user, agreedTerms);


        // then
        assertEquals(user, userRepository.findById(savedUserId).get());
    }

    @Test(expected = DuplicatedUserException.class)
    public void 중복_회원가입_예외() throws Exception {

        // given
        User user1 = new User();
        user1.setId("sangjun");
        user1.setEmail("soma@gmail.com");
        user1.setNickname("testNickname");
        user1.setPhoneNumber("010-1234-5678");
        user1.setGender(0);
        user1.setBirthday(LocalDateTime.now());

        List<TermsOfService> agreedTerms = new ArrayList<>();

        User user2 = new User();
        user2.setId("sangjun");
        user2.setEmail("soma2@gmail.com");
        user2.setNickname("testNickname2");
        user2.setPhoneNumber("010-1234-5678");
        user2.setGender(0);
        user2.setBirthday(LocalDateTime.now());


        // when
        userService.signUp(user1, agreedTerms);
        userService.signUp(user2, agreedTerms); // 여기서 중복 회원 예외가 발생해야 함.


        // then
        // expected = DuplicatedUserException에 의한 중복 유저 오류 발생 검증.
    }

    @Test(expected = DuplicatedEmailException.class)
    public void 중복_이메일_예외() throws Exception {

        // given
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setGender(0);
        user.setBirthday(LocalDateTime.now());

        List<TermsOfService> agreedTerms = new ArrayList<>();

        String savedUserId = userService.signUp(user, agreedTerms);


        // when
        userService.checkEmailExistence("soma@gmail.com"); // 여기서 예외가 발생해야 함.


        // then
        // expected = DuplicatedEmailException에 의한 중복 이메일 오류 발생 검증.
    }

    @Test(expected = NotExistedUserException.class)
    public void 없는_유저_조회_예외() throws Exception {

        // given


        // when
        userService.findById("내가누구게"); // 여기서 예외가 발생해야 함.


        // then
        // expected = NotExistedUserException에 의한 존재하지 않는 유저 오류 발생 검증.
    }

    @Test(expected = DuplicatedNicknameException.class)
    public void 닉네임_중복_확인() throws Exception {

        // given
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setGender(0);
        user.setBirthday(LocalDateTime.now());

        List<TermsOfService> agreedTerms = new ArrayList<>();

        String savedUserId = userService.signUp(user, agreedTerms);


        // when
        userService.checkNicknameExistence("testNickname");


        // then
        // expected = DuplicatedNicknameException에 의한 중복 닉네임 오류 발생 검증.
    }

    @Test
    public void 유저_정보_수정() throws Exception {

        // given
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setGender(0);
        user.setBirthday(LocalDateTime.now());

        List<TermsOfService> agreedTerms = new ArrayList<>();

        String savedUserId = userService.signUp(user, agreedTerms);


        // when
        userService.updateUserInformation(
                new UserApiController.UpdateUserInformationRequest(
                        savedUserId,
                        "changedNickname",
                        "010-1111-1111",
                        0,
                        LocalDateTime.now()));


        // then
        assertEquals(user.getNickname(), "changedNickname");
        assertEquals(user.getPhoneNumber(), "010-1111-1111");
    }

    @Test
    public void 공간_북마크하기() throws Exception {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 공간 정보 저장
        Place place = new Place();
        place.setUser(user);
        place.setName("테스트네임");
        place.setAddress("테스트주소");
        place.setLongitude(127.0);
        place.setLatitude(37.0);
        place.setLocationCode(333);
        placeRepository.save(place);


        // when
        userService.createPlaceBookmark(user, place);
        boolean isBookmark = placeService.checkBookmark(user.getId(), place.getId());

        // then
        assertEquals(isBookmark, true);
    }

    @Test(expected = DuplicatedPlaceBookmarkException.class)
    public void 공간_북마크_중복_예외() throws Exception {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 공간 정보 저장
        Place place = new Place();
        place.setUser(user);
        place.setName("테스트네임");
        place.setAddress("테스트주소");
        place.setLongitude(127.0);
        place.setLatitude(37.0);
        place.setLocationCode(333);
        placeRepository.save(place);


        // when
        userService.createPlaceBookmark(user, place);
        userService.createPlaceBookmark(user, place); // 여기서 오류 발생.


        // then
        // expected = DuplicatedPlaceBookmarkException에 의한 중복 북마크 오류 발생 검증.
    }

    @Test
    public void 공간_북마크_삭제하기() throws Exception {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 공간 정보 저장
        Place place = new Place();
        place.setUser(user);
        place.setName("테스트네임");
        place.setAddress("테스트주소");
        place.setLongitude(127.0);
        place.setLatitude(37.0);
        place.setLocationCode(333);
        placeRepository.save(place);

        // 북마크 정보 저장
        PlaceBookmark placeBookmark = new PlaceBookmark(user, place);
        placeBookmarkRepository.save(placeBookmark);


        // when
        boolean isBookmarkBefore = placeService.checkBookmark(user.getId(), place.getId());

        userService.deletePlaceBookmark(user, place);

        boolean isBookmarkAfter = placeService.checkBookmark(user.getId(), place.getId());

        // then
        assertEquals(isBookmarkBefore, true);
        assertEquals(isBookmarkAfter, false);
    }

    @Test(expected = NotExistedPlaceBookmarkException.class)
    public void 없는_북마크_삭제_예외() throws Exception {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 공간 정보 저장
        Place place = new Place();
        place.setUser(user);
        place.setName("테스트네임");
        place.setAddress("테스트주소");
        place.setLongitude(127.0);
        place.setLatitude(37.0);
        place.setLocationCode(333);
        placeRepository.save(place);


        // when
        userService.deletePlaceBookmark(user, place); // 여기서 오류 발생.


        // then
        // expected = NotExistedPlaceBookmarkException에 의한 존재하지 않는 북마크 삭제 오류 발생 검증.
    }

    @Test
    public void 북마크한_공간_리스트_조회() throws Exception {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 공간 정보 저장
        Place place = new Place();
        place.setUser(user);
        place.setName("테스트네임");
        place.setAddress("테스트주소");
        place.setLongitude(127.0);
        place.setLatitude(37.0);
        place.setLocationCode(333);

        Place place2 = new Place();
        place2.setUser(user);
        place2.setName("테스트네임2");
        place2.setAddress("테스트주소2");
        place2.setLongitude(127.0);
        place2.setLatitude(37.0);
        place2.setLocationCode(333);
        placeRepository.save(place);
        placeRepository.save(place2);

        // 북마크 정보 저장
        PlaceBookmark placeBookmark = new PlaceBookmark(user, place);
        PlaceBookmark placeBookmark2 = new PlaceBookmark(user, place2);
        placeBookmarkRepository.save(placeBookmark);
        placeBookmarkRepository.save(placeBookmark2);


        // when
        List<PlaceBookmark> placeBookmarkList = userService.readAllPlaceBookmark(user);


        // then;
        assertEquals(placeBookmarkList.get(0), placeBookmark);
        assertEquals(placeBookmarkList.get(1), placeBookmark2);
    }

    @Test
    public void 공간_좋아요_누르기() throws Exception {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 공간 정보 저장
        Place place = new Place();
        place.setUser(user);
        place.setName("테스트네임");
        place.setAddress("테스트주소");
        place.setLongitude(127.0);
        place.setLatitude(37.0);
        place.setLocationCode(333);
        placeRepository.save(place);


        // when
        userService.createPlaceFavorite(user, place);
        boolean isFavorite = placeService.checkFavorite(user.getId(), place.getId());

        // then
        assertEquals(isFavorite, true);
    }

    @Test(expected = DuplicatedPlaceFavoriteException.class)
    public void 공간_좋아요_중복_예외() throws Exception {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 공간 정보 저장
        Place place = new Place();
        place.setUser(user);
        place.setName("테스트네임");
        place.setAddress("테스트주소");
        place.setLongitude(127.0);
        place.setLatitude(37.0);
        place.setLocationCode(333);
        placeRepository.save(place);


        // when
        userService.createPlaceFavorite(user, place);
        userService.createPlaceFavorite(user, place); // 여기서 오류 발생.


        // then
        // expected = DuplicatedPlaceFavoriteException에 의한 중복 좋아요 오류 발생 검증.
    }

    @Test
    public void 공간_좋아요_삭제하기() throws Exception {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 공간 정보 저장
        Place place = new Place();
        place.setUser(user);
        place.setName("테스트네임");
        place.setAddress("테스트주소");
        place.setLongitude(127.0);
        place.setLatitude(37.0);
        place.setLocationCode(333);
        placeRepository.save(place);

        // 좋아요 정보 저장
        PlaceFavorite placeFavorite = new PlaceFavorite(user, place);
        placeFavoriteRepository.save(placeFavorite);


        // when
        boolean isFavoriteBefore = placeService.checkFavorite(user.getId(), place.getId());

        userService.deletePlaceFavorite(user, place);

        boolean isFavoriteAfter = placeService.checkFavorite(user.getId(), place.getId());

        // then
        assertEquals(isFavoriteBefore, true);
        assertEquals(isFavoriteAfter, false);
    }

    @Test(expected = NotExistedPlaceFavoriteException.class)
    public void 없는_좋아요_삭제_예외() throws Exception {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 공간 정보 저장
        Place place = new Place();
        place.setUser(user);
        place.setName("테스트네임");
        place.setAddress("테스트주소");
        place.setLongitude(127.0);
        place.setLatitude(37.0);
        place.setLocationCode(333);
        placeRepository.save(place);


        // when
        userService.deletePlaceFavorite(user, place); // 여기서 오류 발생.


        // then
        // expected = NotExistedPlaceFavoriteException에 의한 존재하지 않는 좋아요 삭제 오류 발생 검증.
    }
}