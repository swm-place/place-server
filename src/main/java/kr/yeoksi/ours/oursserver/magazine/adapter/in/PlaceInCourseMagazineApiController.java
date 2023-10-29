package kr.yeoksi.ours.oursserver.magazine.adapter.in;


import kr.yeoksi.ours.oursserver.magazine.service.port.in.PlaceInCourseMagazineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/magazine/courses/{courseId}/places")
@RequiredArgsConstructor
public class PlaceInCourseMagazineApiController {

    private final PlaceInCourseMagazineService placeInCourseMagazineService;


}
