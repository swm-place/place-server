package kr.yeoksi.ours.oursserver.service;

import kr.yeoksi.ours.oursserver.domain.*;
import kr.yeoksi.ours.oursserver.exception.NotExistedPlaceException;
import kr.yeoksi.ours.oursserver.repository.*;
import org.checkerframework.checker.units.qual.A;
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
public class PlaceServiceTest {

    @Autowired PlaceService placeService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    TermsOfServiceRepository termsOfServiceRepository;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    PlaceImgRepository placeImgRepository;
    @Autowired
    HashtagRepository hashtagRepository;
    @Autowired
    HashtagAtPlaceRepository hashtagAtPlaceRepository;


    @Test(expected = NotExistedPlaceException.class)
    public void 없는_공간_조회_예외() {

        // given


        // when
        placeService.findById(324L); // 여기서 예외가 발생해야 함.

        // then
        // expected = NotExistedPlaceException에 의한 존재하지 않는 공간 오류 발생 검증.
    }

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

        // 약관 정보 저장
        List<TermsOfService> agreedTerms = new ArrayList<>();
        TermsOfService termA = new TermsOfService();
        termA.setTitle("테스트 타이틀");
        termA.setContents("테스트 내용");
        termA.setType("테스트 타입");
        termA.setVersion(1);

        termsOfServiceRepository.save(termA);

        agreedTerms.add(termA);
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

        // 약관 정보 저장
        List<TermsOfService> agreedTerms = new ArrayList<>();
        TermsOfService termA = new TermsOfService();
        termA.setTitle("테스트 타이틀");
        termA.setContents("테스트 내용");
        termA.setType("테스트 타입");
        termA.setVersion(1);

        termsOfServiceRepository.save(termA);

        agreedTerms.add(termA);
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
        List<Hashtag> hashtagList = placeService.getHashtagList(place.getId());

        // then
        assertEquals(hashtag, hashtagList.get(0));
    }
}