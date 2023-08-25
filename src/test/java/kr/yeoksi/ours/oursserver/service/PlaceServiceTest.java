package kr.yeoksi.ours.oursserver.service;

import kr.yeoksi.ours.oursserver.domain.*;
import kr.yeoksi.ours.oursserver.exception.NotExistedPlaceException;
import kr.yeoksi.ours.oursserver.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlaceServiceTest {

    @Autowired PlaceService placeService;

    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PlaceBookmarkRepository placeBookmarkRepository;
    @Autowired
    PlaceInBookmarkRepository placeInBookmarkRepository;
    @Autowired
    PlaceFavoriteRepository placeFavoriteRepository;

    @Test
    public void 엘라스틱id로_DB_장소_정보_조회() {

        // given

        // 공간 정보 저장
        Place place = new Place();
        place.setElasticId("elasticId");
        place.setName("테스트네임");
        placeRepository.save(place);


        // when
        Place getPlace = placeService.findByElasticId("elasticId");

        // then
        assertEquals(place, getPlace);
    }

    @Test(expected = NotExistedPlaceException.class)
    public void 엘라스틱id로_없는_장소_조회_예외() {

        // given


        // when
        placeService.findByElasticId("elasticId"); // 여기서 예외가 발생해야 함.

        // then
        // expected = NotExistedPlaceException에 의한 존재하지 않는 공간 오류 발생 검증.
    }

    @Test
    public void 장소_좋아요_개수_조회() {

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
        place.setElasticId("elasticId");
        place.setName("테스트네임");
        placeRepository.save(place);

        // 좋아요 정보 저장;
        PlaceFavorite placeFavorite1 = new PlaceFavorite();
        placeFavorite1.setPlace(place);
        placeFavorite1.setUser(user);

        PlaceFavorite placeFavorite2 = new PlaceFavorite();
        placeFavorite2.setPlace(place);
        placeFavorite2.setUser(user2);

        placeFavoriteRepository.save(placeFavorite1);
        placeFavoriteRepository.save(placeFavorite2);


        // when
        int favoriteCount = placeService.getFavoriteCount(place.getId());


        // then
        assertEquals(2, favoriteCount);
    }



    /*


    @Autowired
    TermsOfServiceRepository termsOfServiceRepository;
    @Autowired
    PlaceImgRepository placeImgRepository;
    @Autowired
    HashtagRepository hashtagRepository;
    @Autowired
    HashtagAtPlaceRepository hashtagAtPlaceRepository;
    @Autowired
    PlaceOpenRepository placeOpenRepository;
    @Autowired
    PlaceReviewRepository placeReviewRepository;
    @Autowired
    PlaceReviewFavoriteRepository placeReviewFavoriteRepository;


    @Test
    public void 공간에_매핑된_이미지_조회() {

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


        // 공간 이미지 정보 저장
        PlaceImg placeImg1 = new PlaceImg();
        placeImg1.setPlace(place);
        placeImg1.setImgUrl("placeImg1.url");
        placeImgRepository.save(placeImg1);


        // when
        List<String> imgUrls = placeService.getImgUrlList(place.getId());


        // then
        assertEquals(placeImg1.getImgUrl(), imgUrls.get(0));
    }

    @Test
    public void 공간에_매핑된_해시태그_조회() {

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

        // 해시태그 정보 저장
        Hashtag hashtag = new Hashtag();
        hashtag.setName("테스트해시태그");
        hashtag.setImgUrl("테스트.url");
        hashtagRepository.save(hashtag);

        // 해시태그-공간 매핑 정보 저장
        HashtagAtPlace hashtagAtPlace = new HashtagAtPlace();
        hashtagAtPlace.setPlace(place);
        hashtagAtPlace.setHashtag(hashtag);
        hashtagAtPlaceRepository.save(hashtagAtPlace);


        // when
        List<String> hashtagList = placeService.getHashtagList(place.getId());

        // then
        assertEquals(hashtag.getName(), hashtagList.get(0));
    }

    @Test
    public void 공간의_운영중_응답_개수_조회() {

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
        place.setUser(user);
        place.setName("테스트네임");
        place.setAddress("테스트주소");
        place.setLongitude(127.0);
        place.setLatitude(37.0);
        place.setLocationCode(333);
        placeRepository.save(place);

        // 운영중 정보 저장
        PlaceOpen placeOpen1 = new PlaceOpen();
        placeOpen1.setPlace(place);
        placeOpen1.setUser(user);

        PlaceOpen placeOpen2 = new PlaceOpen();
        placeOpen2.setPlace(place);
        placeOpen2.setUser(user2);

        placeOpenRepository.save(placeOpen1);
        placeOpenRepository.save(placeOpen2);


        // when
        int openCount = placeService.getOpenCount(place.getId());


        // then
        assertEquals(openCount, 2);
    }

    @Test
    public void 공간에_매핑된_한줄평_조회() {

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


        // 한줄평 정보 저장
        PlaceReview placeReview = new PlaceReview();
        placeReview.setPlace(place);
        placeReview.setUser(user);
        placeReview.setContents("테스트리뷰");
        placeReviewRepository.save(placeReview);


        // when
        List<PlaceReview> placeReviewList = placeService.getAllPlaceReviewList(place.getId());


        // then
        assertEquals(placeReview, placeReviewList.get(0));
    }

    @Test
    public void 유저가_한줄평에_좋아요했는지_확인() {

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
        place.setUser(user);
        place.setName("테스트네임");
        place.setAddress("테스트주소");
        place.setLongitude(127.0);
        place.setLatitude(37.0);
        place.setLocationCode(333);
        placeRepository.save(place);

        // 운영중 정보 저장
        PlaceOpen placeOpen = new PlaceOpen();
        placeOpen.setUser(user);
        placeOpen.setPlace(place);
        placeOpenRepository.save(placeOpen);


        // when
        boolean check1 = placeService.checkOpen(user.getId(), place.getId());
        boolean check2 = placeService.checkOpen(user2.getId(), place.getId());


        // then
        assertEquals(check1, true);
        assertEquals(check2, false);
    }

     */
}