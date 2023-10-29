package kr.yeoksi.ours.oursserver.magazine.service;

import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineService;
import kr.yeoksi.ours.oursserver.others.service.PlaceService;
import kr.yeoksi.ours.oursserver.others.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CourseMagazineServiceIntegrationTest {

    @Autowired CourseMagazineService courseMagazineService;

    @Autowired PlaceService placeService;
    @Autowired UserService userService;


}
