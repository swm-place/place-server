package kr.yeoksi.ours.oursserver.others.service;

import kr.yeoksi.ours.oursserver.others.controller.UserApiController;
import kr.yeoksi.ours.oursserver.exception.*;
import kr.yeoksi.ours.oursserver.others.domain.*;
import kr.yeoksi.ours.oursserver.others.domain.dto.place.response.ThumbnailInfoResponse;
import kr.yeoksi.ours.oursserver.others.exception.*;
import kr.yeoksi.ours.oursserver.others.repository.*;
import kr.yeoksi.ours.oursserver.others.service.PlaceService;
import kr.yeoksi.ours.oursserver.others.service.UserService;
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

    @Autowired
    UserService userService;
    @Autowired
    PlaceService placeService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    TermsOfServiceRepository termsOfServiceRepository;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    PlaceBookmarkRepository placeBookmarkRepository;
    @Autowired
    PlaceFavoriteRepository placeFavoriteRepository;
    @Autowired
    PlaceInBookmarkRepository placeInBookmarkRepository;
    @Autowired
    PlaceOpenRepository placeOpenRepository;

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
                "sangjun",

                new UserApiController.UpdateUserInformationRequest(
                        savedUserId,
                        "changedNickname",
                        "010-1111-1111",
                        0,
                        LocalDateTime.now()));


        // then
        assertEquals(user.getNickname(), "changedNickname");
        assertEquals(user.getPhoneNumber(), "010-1111-1111");
        assertEquals(0, user.getGender());
    }

    @Test(expected = InsufficientPrivilegesException.class)
    public void 다른_유저_정보_수정() throws Exception {

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
                "sangjun2",

                new UserApiController.UpdateUserInformationRequest(
                        savedUserId,
                        "changedNickname",
                        "010-1111-1111",
                        0,
                        LocalDateTime.now()));


        // then
        // expected = InsufficientPrivilegesException에 의한 다른 유저 정보 수정 오류 발생 검증.
    }

    /*
    @Test
    public void 유저_장소_북마크_여부_확인() {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());

        User user2 = new User();
        user2.setId("sangjun2");
        user2.setEmail("soma2@gmail.com");
        user2.setNickname("testNickname2");
        user2.setPhoneNumber("010-1234-5673");
        user2.setBirthday(LocalDateTime.now());
        userRepository.save(user);
        userRepository.save(user2);

        // 공간 정보 저장
        Place place = new Place();
        place.setId("placeId");
        place.setName("테스트네임");
        placeRepository.save(place);

        // 북마크 정보 저장
        PlaceBookmark placeBookmark = new PlaceBookmark();
        placeBookmark.setUser(user);
        placeBookmark.setTitle("타이틀1");

        PlaceBookmark placeBookmark2 = new PlaceBookmark();
        placeBookmark2.setUser(user2);
        placeBookmark.setTitle("타이틀2");
        placeBookmarkRepository.save(placeBookmark);
        placeBookmarkRepository.save(placeBookmark2);

        PlaceInBookmark placeInBookmark = new PlaceInBookmark();
        placeInBookmark.setPlace(place);
        placeInBookmark.setPlaceBookmark(placeBookmark);
        placeInBookmarkRepository.save(placeInBookmark);


        // when
        boolean isBookmark = userService.checkBookmark(user.getId(), place.getId());
        boolean isBookmark2 = userService.checkBookmark(user2.getId(), place.getId());


        // then
        assertEquals(true, isBookmark);
        assertEquals(false, isBookmark2);
    }

     */

    /*
    @Test
    public void 공간_북마크_삭제() {

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
        place.setId("placeId");
        place.setName("테스트네임");
        placeRepository.save(place);

        // 북마크 정보 저장
        PlaceBookmark placeBookmark = new PlaceBookmark();
        placeBookmark.setUser(user);
        placeBookmark.setTitle("타이틀1");
        placeBookmarkRepository.save(placeBookmark);

        PlaceInBookmark placeInBookmark = new PlaceInBookmark();
        placeInBookmark.setPlace(place);
        placeInBookmark.setPlaceBookmark(placeBookmark);
        placeInBookmarkRepository.save(placeInBookmark);


        // when
        userService.deletePlaceInBookmark(place.getId(), placeBookmark.getId());
        boolean isBookmark = userService.checkBookmark(user.getId(), place.getId());


        // then
        assertEquals(false, isBookmark);
    }

     */

    /*
    @Test
    public void 유저_장소_좋아요_여부_확인() {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());

        User user2 = new User();
        user2.setId("sangjun2");
        user2.setEmail("soma2@gmail.com");
        user2.setNickname("testNickname2");
        user2.setPhoneNumber("010-1234-5673");
        user2.setBirthday(LocalDateTime.now());
        userRepository.save(user);
        userRepository.save(user2);

        // 공간 정보 저장
        Place place = new Place();
        place.setId("placeId");
        place.setName("테스트네임");
        placeRepository.save(place);

        // 좋아요 정보 저장
        PlaceFavorite placeFavorite = new PlaceFavorite();
        placeFavorite.setUser(user);
        placeFavorite.setPlace(place);
        placeFavoriteRepository.save(placeFavorite);


        // when
        boolean check1 = userService.checkFavorite(user.getId(), place.getId());
        boolean check2 = userService.checkFavorite(user2.getId(), place.getId());


        // then
        assertEquals(true, check1);
        assertEquals(false, check2);
    }
     */

    /*
    @Test
    public void 유저_장소_운영중_응답_여부_확인() {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());

        User user2 = new User();
        user2.setId("sangjun2");
        user2.setEmail("soma2@gmail.com");
        user2.setNickname("testNickname2");
        user2.setPhoneNumber("010-1234-5673");
        user2.setBirthday(LocalDateTime.now());
        userRepository.save(user);
        userRepository.save(user2);

        // 공간 정보 저장
        Place place = new Place();
        place.setId("placeId");
        place.setName("테스트네임");
        placeRepository.save(place);

        // 운영중 정보 저장
        PlaceOpen placeOpen = new PlaceOpen();
        placeOpen.setUser(user);
        placeOpen.setPlace(place);
        placeOpenRepository.save(placeOpen);


        // when
        boolean check1 = userService.checkOpen(user.getId(), place.getId());
        boolean check2 = userService.checkOpen(user2.getId(), place.getId());


        // then
        assertEquals(true, check1);
        assertEquals(false, check2);
    }
     */


    @Test
    public void 북마크_그룹_썸네일_조회() {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 북마크 정보 저장
        PlaceBookmark placeBookmark = new PlaceBookmark();
        placeBookmark.setUser(user);
        placeBookmark.setTitle("타이틀1");

        placeBookmarkRepository.save(placeBookmark);

        // 장소 정보 저장
        Place place1 = Place.builder()
                .id("test1")
                .name("test1")
                .category("test1")
                .build();
        Place place2 = Place.builder()
                .id("test2")
                .name("test2")
                .category("test2")
                .build();
        Place place3 = Place.builder()
                .id("test3")
                .name("test3")
                .category("test3")
                .build();
        Place place4 = Place.builder()
                .id("test4")
                .name("test4")
                .category("test4")
                .build();
        Place place5 = Place.builder()
                .id("test5")
                .name("test5")
                .category("test5")
                .build();
        Place place6 = Place.builder()
                .id("test6")
                .name("test6")
                .category("test6")
                .build();
        place1.setId(placeRepository.save(place1));
        place2.setId(placeRepository.save(place2));
        place3.setId(placeRepository.save(place3));
        place4.setId(placeRepository.save(place4));
        place5.setId(placeRepository.save(place5));
        place6.setId(placeRepository.save(place6));

        // placeInBookmark 저장
        PlaceInBookmark placeInBookmark1 = new PlaceInBookmark();
        placeInBookmark1.setPlace(place1);
        placeInBookmark1.setPlaceBookmark(placeBookmark);

        PlaceInBookmark placeInBookmark2 = new PlaceInBookmark();
        placeInBookmark2.setPlace(place2);
        placeInBookmark2.setPlaceBookmark(placeBookmark);

        PlaceInBookmark placeInBookmark3 = new PlaceInBookmark();
        placeInBookmark3.setPlace(place3);
        placeInBookmark3.setPlaceBookmark(placeBookmark);

        PlaceInBookmark placeInBookmark4 = new PlaceInBookmark();
        placeInBookmark4.setPlace(place4);
        placeInBookmark4.setPlaceBookmark(placeBookmark);

        PlaceInBookmark placeInBookmark5 = new PlaceInBookmark();
        placeInBookmark5.setPlace(place5);
        placeInBookmark5.setPlaceBookmark(placeBookmark);

        PlaceInBookmark placeInBookmark6 = new PlaceInBookmark();
        placeInBookmark6.setPlace(place6);
        placeInBookmark6.setPlaceBookmark(placeBookmark);

        placeInBookmarkRepository.save(placeInBookmark1);
        placeInBookmarkRepository.save(placeInBookmark2);
        placeInBookmarkRepository.save(placeInBookmark3);
        placeInBookmarkRepository.save(placeInBookmark4);
        placeInBookmarkRepository.save(placeInBookmark5);
        placeInBookmarkRepository.save(placeInBookmark6);

        // when
        List<ThumbnailInfoResponse> thumbnailInfo = userService.getThumbnailInfo(placeBookmark.getId());

        // then
        assertEquals(4, thumbnailInfo.size());
        assertEquals("test6", thumbnailInfo.get(0).getPlaceName());
        assertEquals("test5", thumbnailInfo.get(1).getPlaceName());
        assertEquals("test4", thumbnailInfo.get(2).getPlaceName());
        assertEquals("test3", thumbnailInfo.get(3).getPlaceName());
    }

    @Test
    public void 북마크_그룹_조회_페이지네이션() {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 북마크 정보 저장
        PlaceBookmark placeBookmark = new PlaceBookmark();
        placeBookmark.setUser(user);
        placeBookmark.setTitle("타이틀1");

        PlaceBookmark placeBookmark2 = new PlaceBookmark();
        placeBookmark2.setUser(user);
        placeBookmark2.setTitle("타이틀2");

        PlaceBookmark placeBookmark3 = new PlaceBookmark();
        placeBookmark3.setUser(user);
        placeBookmark3.setTitle("타이틀3");

        PlaceBookmark placeBookmark4 = new PlaceBookmark();
        placeBookmark4.setUser(user);
        placeBookmark4.setTitle("타이틀4");

        PlaceBookmark placeBookmark5 = new PlaceBookmark();
        placeBookmark5.setUser(user);
        placeBookmark5.setTitle("타이틀5");

        PlaceBookmark placeBookmark6 = new PlaceBookmark();
        placeBookmark6.setUser(user);
        placeBookmark6.setTitle("타이틀6");

        PlaceBookmark placeBookmark7 = new PlaceBookmark();
        placeBookmark7.setUser(user);
        placeBookmark7.setTitle("타이틀7");

        PlaceBookmark placeBookmark8 = new PlaceBookmark();
        placeBookmark8.setUser(user);
        placeBookmark8.setTitle("타이틀8");

        PlaceBookmark placeBookmark9 = new PlaceBookmark();
        placeBookmark9.setUser(user);
        placeBookmark9.setTitle("타이틀9");

        PlaceBookmark placeBookmark10 = new PlaceBookmark();
        placeBookmark10.setUser(user);
        placeBookmark10.setTitle("타이틀10");

        PlaceBookmark placeBookmark11 = new PlaceBookmark();
        placeBookmark11.setUser(user);
        placeBookmark11.setTitle("타이틀11");

        placeBookmarkRepository.save(placeBookmark);
        placeBookmarkRepository.save(placeBookmark2);
        placeBookmarkRepository.save(placeBookmark3);
        placeBookmarkRepository.save(placeBookmark4);
        placeBookmarkRepository.save(placeBookmark5);
        placeBookmarkRepository.save(placeBookmark6);
        placeBookmarkRepository.save(placeBookmark7);
        placeBookmarkRepository.save(placeBookmark8);
        placeBookmarkRepository.save(placeBookmark9);
        placeBookmarkRepository.save(placeBookmark10);
        placeBookmarkRepository.save(placeBookmark11);


        // when
        List<PlaceBookmark> placeBookmarks1 = userService.readAllMyPlaceBookmark("sangjun", 0, 10);
        List<PlaceBookmark> placeBookmarks2 = userService.readAllMyPlaceBookmark("sangjun", 1, 10);
        List<PlaceBookmark> placeBookmarks3 = userService.readAllMyPlaceBookmark("sangjun", 0, 6);
        List<PlaceBookmark> placeBookmarks4 = userService.readAllMyPlaceBookmark("sangjun", 1, 6);

        // then
        assertEquals(10, placeBookmarks1.size());
        assertEquals(1, placeBookmarks2.size());
        assertEquals(6, placeBookmarks3.size());
        assertEquals(5, placeBookmarks4.size());
    }

    @Test
    public void 장소의_북마크_그룹_포함여부_확인() throws Exception {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 북마크 정보 저장
        PlaceBookmark placeBookmark = new PlaceBookmark();
        placeBookmark.setUser(user);
        placeBookmark.setTitle("타이틀1");

        placeBookmarkRepository.save(placeBookmark);

        // 장소 정보 저장
        Place place1 = Place.builder()
                .id("test1")
                .name("test1")
                .category("test1")
                .build();
        Place place2 = Place.builder()
                .id("test2")
                .name("test2")
                .category("test2")
                .build();
        place1.setId(placeRepository.save(place1));
        place2.setId(placeRepository.save(place2));

        // placeInBookmark 저장
        PlaceInBookmark placeInBookmark1 = new PlaceInBookmark();
        placeInBookmark1.setPlace(place1);
        placeInBookmark1.setPlaceBookmark(placeBookmark);
        placeInBookmarkRepository.save(placeInBookmark1);


        // when
        boolean check1 = userService.checkBookmarkAtGroup(placeBookmark.getId(), place1.getId());
        boolean check2 = userService.checkBookmarkAtGroup(placeBookmark.getId(), place2.getId());
        boolean check3 = userService.checkBookmarkAtGroup(placeBookmark.getId(), "");

        // then
        assertEquals(true, check1);
        assertEquals(false, check2);
        assertEquals(false, check3);
    }

    @Test
    public void 북마크_타이틀을_수정할_수_있다() {
        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 북마크 정보 저장
        PlaceBookmark placeBookmark = new PlaceBookmark();
        placeBookmark.setUser(user);
        placeBookmark.setTitle("타이틀1");
        placeBookmarkRepository.save(placeBookmark);


        // when
        userService.updatePlaceBookmark(placeBookmark, "변경된_타이틀");


        // then
        assertEquals("변경된_타이틀", placeBookmark.getTitle());
    }

    /*
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
        place.setImgUrl("imgurl");
        placeRepository.save(place);


        // when
        userService.createPlaceFavorite(user, place);
        boolean isFavorite = placeService.checkFavorite(user.getId(), place.getId());

        // then
        assertEquals(isFavorite, true);
    }
     */

    /*
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

     */

    /*
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

     */

    /*
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

     */

    /*
    @Test
    public void 공간_북마크_그룹_생성() throws Exception {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 공간 북마크 그룹 정보 저장
        String title = "테스트타이틀";

        PlaceBookmark placeBookmark = new PlaceBookmark();
        placeBookmark.setUser(user);
        placeBookmark.setTitle(title);


        // when
        Long savedBookmarkId = userService.createPlaceBookmark(placeBookmark);


        // then
        assertEquals(placeBookmark, placeBookmarkRepository.findById(savedBookmarkId).get());
    }

     */

    /*
    @Test
    public void 공간_북마크_그룹_조회() throws Exception {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 공간 북마크 그룹 정보 저장
        String title = "테스트타이틀";

        PlaceBookmark placeBookmark = new PlaceBookmark();
        placeBookmark.setUser(user);
        placeBookmark.setTitle(title);
        placeBookmarkRepository.save(placeBookmark);


        // when
        PlaceBookmark getPlaceBookmark = userService.getPlaceBookmark(placeBookmark.getId());


        // then
        assertEquals(getPlaceBookmark, placeBookmark);
    }

     */

    /*
    @Test(expected = NotExistedPlaceBookmarkException.class)
    public void 없는_공간_북마크_그룹_조회_예외() throws Exception {

        // given


        // when
        PlaceBookmark placeBookmark = userService.getPlaceBookmark(324L);

        // then
        // expected = NotExistedPlaceBookmarkException에 의한 존재하지 않는 공간 북마크 그룹 조회 오류 발생 검증.
    }

    @Test(expected = NotExistedPlaceBookmarkException.class)
    public void 공간_북마크_그룹_삭제() throws Exception {

        // given

        // 유저 정보 저장
        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setBirthday(LocalDateTime.now());
        userRepository.save(user);

        // 공간 북마크 그룹 정보 저장
        String title = "테스트타이틀";

        PlaceBookmark placeBookmark = new PlaceBookmark();
        placeBookmark.setUser(user);
        placeBookmark.setTitle(title);
        placeBookmarkRepository.save(placeBookmark);


        // when
        userService.deletePlaceBookmark(placeBookmark);
        userService.getPlaceBookmark(placeBookmark.getId());

        // then
        // expected = NotExistedPlaceBookmarkException에 의한 공간 북마크 그룹 삭제 성공 검증.
    }

     */
}