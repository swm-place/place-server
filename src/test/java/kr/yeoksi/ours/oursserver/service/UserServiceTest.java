package kr.yeoksi.ours.oursserver.service;

import kr.yeoksi.ours.oursserver.domain.TermsOfService;
import kr.yeoksi.ours.oursserver.domain.User;
import kr.yeoksi.ours.oursserver.exception.DuplicatedEmailException;
import kr.yeoksi.ours.oursserver.exception.DuplicatedUserException;
import kr.yeoksi.ours.oursserver.repository.TermsOfServiceRepository;
import kr.yeoksi.ours.oursserver.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Autowired
    TermsOfServiceRepository termsOfServiceRepository;

    /**
     * 회원 가입
     */
    @Test
    public void signUp() throws Exception {

        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setGender(0);
        user.setBirthday("19980309");

        List<TermsOfService> agreedTerms = new ArrayList<>();

        TermsOfService termA = new TermsOfService();
        termA.setTitle("테스트 타이틀");
        termA.setContents("테스트 내용");
        termA.setType("테스트 타입");
        termA.setVersion(1);
        termA.setRequired(0);

        termsOfServiceRepository.save(termA);


        TermsOfService termB = new TermsOfService();
        termB.setTitle("테스트 타이틀");
        termB.setContents("테스트 내용");
        termB.setType("테스트 타입");
        termB.setVersion(1);
        termB.setRequired(0);

        termsOfServiceRepository.save(termB);

        agreedTerms.add(termA);
        agreedTerms.add(termB);

        String savedUserId = userService.signUp(user, agreedTerms);

        assertEquals(user, userRepository.findById(savedUserId));
    }

    /**
     * 중복 회원 예외 확인
     */
    @Test(expected = DuplicatedUserException.class)
    public void 중복_회원가입_예외() throws Exception {

        User user1 = new User();
        user1.setId("sangjun");
        user1.setEmail("soma@gmail.com");
        user1.setNickname("testNickname");
        user1.setPhoneNumber("010-1234-5678");
        user1.setGender(0);
        user1.setBirthday("19980309");

        List<TermsOfService> agreedTerms = new ArrayList<>();

        TermsOfService termA = new TermsOfService();
        termA.setTitle("테스트 타이틀");
        termA.setContents("테스트 내용");
        termA.setType("테스트 타입");
        termA.setVersion(1);
        termA.setRequired(0);

        TermsOfService termB = new TermsOfService();
        termB.setTitle("테스트 타이틀");
        termB.setContents("테스트 내용");
        termB.setType("테스트 타입");
        termB.setVersion(1);
        termB.setRequired(0);

        termsOfServiceRepository.save(termA);
        termsOfServiceRepository.save(termB);

        agreedTerms.add(termA);
        agreedTerms.add(termB);

        User user2 = new User();
        user2.setId("sangjun");
        user2.setEmail("soma2@gmail.com");
        user2.setNickname("testNickname2");
        user2.setPhoneNumber("010-1234-5678");
        user2.setGender(0);
        user2.setBirthday("19980309");

        userService.signUp(user1, agreedTerms);
        userService.signUp(user2, agreedTerms); // 여기서 중복 회원 예외가 발생해야 함.

        fail("중복 회원 예외가 발생해야 한다.");
    }

    /**
     * 이미 존재하는 이메일인지 확인
     */
    @Test(expected = DuplicatedEmailException.class)
    public void 중복_이메일_예외() throws Exception {

        User user = new User();
        user.setId("sangjun");
        user.setEmail("soma@gmail.com");
        user.setNickname("testNickname");
        user.setPhoneNumber("010-1234-5678");
        user.setGender(0);
        user.setBirthday("19980309");

        List<TermsOfService> agreedTerms = new ArrayList<>();

        TermsOfService termA = new TermsOfService();
        termA.setTitle("테스트 타이틀");
        termA.setContents("테스트 내용");
        termA.setType("테스트 타입");
        termA.setVersion(1);
        termA.setRequired(0);

        termsOfServiceRepository.save(termA);


        TermsOfService termB = new TermsOfService();
        termB.setTitle("테스트 타이틀");
        termB.setContents("테스트 내용");
        termB.setType("테스트 타입");
        termB.setVersion(1);
        termB.setRequired(0);

        termsOfServiceRepository.save(termB);

        agreedTerms.add(termA);
        agreedTerms.add(termB);

        String savedUserId = userService.signUp(user, agreedTerms);

        userService.checkEmailExistence("soma@gmail.com"); // 여기서 예외가 발생해야 함.

        fail("중복 이메일 예외가 발생해야 한다.");
    }
}