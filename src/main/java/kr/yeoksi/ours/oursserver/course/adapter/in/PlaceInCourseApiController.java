package kr.yeoksi.ours.oursserver.course.adapter.in;

import jakarta.validation.Valid;
import kr.yeoksi.ours.oursserver.course.adapter.in.request.PlaceInCourseCreateRequest;
import kr.yeoksi.ours.oursserver.course.adapter.in.request.PlaceInCourseUpdateRequest;
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

    @PatchMapping("/{placeInCourseId}")
    public ResponseEntity<PlaceInCourseResponse> updatePlaceInCourse(@RequestHeader("X-User-Uid") String userId,
                                                                     @PathVariable String courseId,
                                                                     @PathVariable String placeInCourseId,
                                                                     @RequestBody @Valid PlaceInCourseUpdateRequest request) {
        PlaceInCourse placeInCourse = request.toPlaceInCourse();
        return ResponseEntity.ok(
                PlaceInCourseResponse.from(
                        placeInCourseService.update(placeInCourse, userId)));
    }

    @DeleteMapping("/{placeInCourseId}")
    public ResponseEntity<?> deletePlaceInCourse(@RequestHeader("X-User-Uid") String userId,
                                                                     @PathVariable Long courseId,
                                                                     @PathVariable Long placeInCourseId) {
        placeInCourseService.delete(placeInCourseId, courseId, userId);
        return ResponseEntity.ok().build();
    }
}
