package kr.yeoksi.ours.oursserver.service;

import kr.yeoksi.ours.oursserver.domain.*;
import kr.yeoksi.ours.oursserver.domain.dto.place.response.ReadPlaceReviewResponse;
import kr.yeoksi.ours.oursserver.exception.NotExistedPlaceException;
import kr.yeoksi.ours.oursserver.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

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
    @Autowired
    PlaceOpenRepository placeOpenRepository;
    @Autowired
    PlaceReviewRepository placeReviewRepository;
    @Autowired PlaceReviewFavoriteRepository placeReviewFavoriteRepository;
    @Autowired PlaceReviewComplainRepository placeReviewComplainRepository;
    @Autowired
    PlaceImgRepository placeImgRepository;

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

    @Test
    public void 장소_운영중_응답_개수_조회() {

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
        assertEquals(2, openCount);
    }

    @Test
    public void 장소에_매핑된_한줄평을_주어진_개수만큼_조회() {

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

        // 한줄평 정보 저장
        PlaceReview placeReview = new PlaceReview();
        placeReview.setPlace(place);
        placeReview.setUser(user);
        placeReview.setContents("테스트리뷰1");

        PlaceReview placeReview2 = new PlaceReview();
        placeReview2.setPlace(place);
        placeReview2.setUser(user);
        placeReview2.setContents("테스트리뷰2");

        PlaceReview placeReview3 = new PlaceReview();
        placeReview3.setPlace(place);
        placeReview3.setUser(user);
        placeReview3.setContents("테스트리뷰3");

        PlaceReview placeReview4 = new PlaceReview();
        placeReview4.setPlace(place);
        placeReview4.setUser(user);
        placeReview4.setContents("테스트리뷰3");
        placeReviewRepository.save(placeReview);
        placeReviewRepository.save(placeReview2);
        placeReviewRepository.save(placeReview3);
        placeReviewRepository.save(placeReview4);

        // placeReview3에 좋아요 다른 유저의 신고하기, placeReview2 신고하기, 다른 유저의 좋아요
        PlaceReviewFavorite placeReviewFavorite = new PlaceReviewFavorite();
        placeReviewFavorite.setPlaceReview(placeReview3);
        placeReviewFavorite.setUser(user);

        PlaceReviewFavorite placeReviewFavorite2 = new PlaceReviewFavorite();
        placeReviewFavorite2.setPlaceReview(placeReview2);
        placeReviewFavorite2.setUser(user2);
        placeReviewFavoriteRepository.save(placeReviewFavorite);
        placeReviewFavoriteRepository.save(placeReviewFavorite2);

        PlaceReviewComplain placeReviewComplain = new PlaceReviewComplain();
        placeReviewComplain.setPlaceReview(placeReview2);
        placeReviewComplain.setUser(user);

        PlaceReviewComplain placeReviewComplain2 = new PlaceReviewComplain();
        placeReviewComplain2.setPlaceReview(placeReview3);
        placeReviewComplain2.setUser(user2);
        placeReviewComplainRepository.save(placeReviewComplain);
        placeReviewComplainRepository.save(placeReviewComplain2);


        // when
        List<ReadPlaceReviewResponse> placeReviewList = placeService.getPlaceReviewList(user.getId(), place.getId(), 3);


        // then
        assertEquals(3, placeReviewList.size());

        assertEquals(placeReview4.getId(), placeReviewList.get(0).getPlaceReviewId());
        assertEquals(false, placeReviewList.get(0).isFavorite());
        assertEquals(false, placeReviewList.get(0).isComplain());

        assertEquals(placeReview3.getId(), placeReviewList.get(1).getPlaceReviewId());
        assertEquals(true, placeReviewList.get(1).isFavorite());
        assertEquals(false, placeReviewList.get(1).isComplain());

        assertEquals(placeReview2.getId(), placeReviewList.get(2).getPlaceReviewId());
        assertEquals(false, placeReviewList.get(2).isFavorite());
        assertEquals(true, placeReviewList.get(2).isComplain());
    }

    @Test
    public void 공간에_매핑된_이미지_주어진_개수만큼_조회() {

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
        place.setElasticId("elasticId");
        place.setName("테스트네임");
        placeRepository.save(place);


        // 공간 이미지 정보 저장
        PlaceImg placeImg1 = new PlaceImg();
        placeImg1.setPlace(place);
        placeImg1.setImgUrl("placeImg1.url");

        PlaceImg placeImg2 = new PlaceImg();
        placeImg2.setPlace(place);
        placeImg2.setImgUrl("placeImg2.url");

        PlaceImg placeImg3 = new PlaceImg();
        placeImg3.setPlace(place);
        placeImg3.setImgUrl("placeImg3.url");
        placeImgRepository.save(placeImg1);
        placeImgRepository.save(placeImg2);
        placeImgRepository.save(placeImg3);


        // when
        List<String> imgUrls = placeService.getImgUrlList(place.getId(),2);


        // then
        assertEquals(2, imgUrls.size());
        assertEquals(placeImg3.getImgUrl(), imgUrls.get(0));
        assertEquals(placeImg2.getImgUrl(), imgUrls.get(1));
    }




    /*


    @Autowired
    TermsOfServiceRepository termsOfServiceRepository;
    @Autowired
    HashtagRepository hashtagRepository;
    @Autowired
    HashtagAtPlaceRepository hashtagAtPlaceRepository;
    @Autowired
    PlaceReviewRepository placeReviewRepository;
    @Autowired
    PlaceReviewFavoriteRepository placeReviewFavoriteRepository;

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
     */
}