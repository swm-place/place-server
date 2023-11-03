package kr.yeoksi.ours.oursserver.course.adapter.in;

import kr.yeoksi.ours.oursserver.course.service.port.in.CourseBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CourseBookmarkApiController {

    private final CourseBookmarkService courseBookmarkService;


}
