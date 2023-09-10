package kr.yeoksi.ours.oursserver.magazine.adapter.in;

import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CourseMagazineApiController {
    CourseMagazineService courseMagazineService;

}
