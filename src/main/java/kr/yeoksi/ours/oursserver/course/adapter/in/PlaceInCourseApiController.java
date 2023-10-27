package kr.yeoksi.ours.oursserver.course.adapter.in;

import jakarta.validation.Valid;
import kr.yeoksi.ours.oursserver.course.adapter.in.request.PlaceInCourseCreateRequest;
import kr.yeoksi.ours.oursserver.course.adapter.in.response.PlaceInCourseResponse;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import kr.yeoksi.ours.oursserver.course.service.port.in.PlaceInCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/courses/{courseId}/places")
public class PlaceInCourseApiController {

    private final PlaceInCourseService placeInCourseService;


    @PostMapping("/{placeInCourseId}")
    public ResponseEntity<PlaceInCourseResponse> addPlaceToCourse(@RequestHeader("X-User-Uid") String userId,
                                                                  @PathVariable String courseId,
                                                                  @PathVariable String placeInCourseId,
                                                                  @RequestBody @Valid PlaceInCourseCreateRequest request) {
        PlaceInCourse placeInCourse = request.toPlaceInCourse();
        return ResponseEntity.ok(
                PlaceInCourseResponse.from(
                        placeInCourseService.append(placeInCourse, userId)));
    }
}
