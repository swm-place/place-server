package kr.yeoksi.ours.oursserver.service;

import kr.yeoksi.ours.oursserver.domain.User;
import kr.yeoksi.ours.oursserver.exception.DuplicatedUserException;
import kr.yeoksi.ours.oursserver.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
public class UserServiceTest {

    //@Autowired UserService userService;
    //@Autowired UserRepository userRepository;

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

        //String savedUserId = userService.signUp(user);

        //assertEquals(user, userRepository.findById(savedUserId));
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

        User user2 = new User();
        user2.setId("sangjun");
        user2.setEmail("soma2@gmail.com");
        user2.setNickname("testNickname2");
        user2.setPhoneNumber("010-1234-5678");

        //userService.signUp(user1);
        //userService.signUp(user2); // 여기서 중복 회원 예외가 발생해야 함.

        fail("중복 회원 예외가 발생해야 한다.");
    }
}