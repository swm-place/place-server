package kr.yeoksi.ours.oursserver.course.service;

import kr.yeoksi.ours.oursserver.course.service.port.in.PlaceInCourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlaceInCourseServiceIntegrationTest {

    @Autowired PlaceInCourseService placeInCourseService;


    @Test
    public void 코스_내_장소를_추가할_수_있다() {
        // given

        // when

        // then
    }

    @Test
    public void 코스_내_장소를_수정할_수_있다() {
        // given

        // when

        // then
    }

    @Test
    public void 코스_내_장소를_삭제할_수_있다() {
        // given

        // when

        // then
    }

    @Test
    public void 코스의_id로_코스_내_모든_장소들을_조회할_수_있다() {
        // given

        // when

        // then
    }

    @Test
    public void id로_코스_내_장소를_조회할_수_있다() {
        // given

        // when

        // then
    }

}
