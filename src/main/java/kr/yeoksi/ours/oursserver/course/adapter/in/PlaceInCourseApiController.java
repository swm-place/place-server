package kr.yeoksi.ours.oursserver.course.adapter.in;

import kr.yeoksi.ours.oursserver.course.service.port.in.PlaceInCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/courses/{courseId}/places")
public class PlaceInCourseApiController {

    private final PlaceInCourseService placeInCourseService;



}
