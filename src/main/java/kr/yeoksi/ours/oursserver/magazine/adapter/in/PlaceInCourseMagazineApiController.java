package kr.yeoksi.ours.oursserver.magazine.adapter.in;


import kr.yeoksi.ours.oursserver.magazine.adapter.in.response.PlaceInCourseMagazineResponse;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.PlaceInCourseMagazineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/magazine/courses/{courseId}/places")
@RequiredArgsConstructor
public class PlaceInCourseMagazineApiController {

    private final PlaceInCourseMagazineService placeInCourseMagazineService;


    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceInCourseMagazineResponse> getPlaceInCourseMagazine(@PathVariable Long courseId,
                                                                                  @PathVariable Long placeId) {
        return ResponseEntity.ok(PlaceInCourseMagazineResponse.from(
                placeInCourseMagazineService.getById(placeId)));
    }
}
