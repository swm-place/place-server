package kr.yeoksi.ours.oursserver.course.adapter.in;

import kr.yeoksi.ours.oursserver.course.service.port.in.CourseInBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CourseInBookmarkApiController {

    private final CourseInBookmarkService courseInBookmarkService;


}
