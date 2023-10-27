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


    @PostMapping
    public ResponseEntity<PlaceInCourseResponse> addPlaceToCourse(@RequestHeader("X-User-Uid") String userId,
                                                                  @PathVariable Long courseId,
                                                                  @RequestBody @Valid PlaceInCourseCreateRequest request) {
        PlaceInCourse placeInCourse = request.toPlaceInCourse();
        placeInCourse.setCourseId(courseId);

        return ResponseEntity.ok(
                PlaceInCourseResponse.from(
                        placeInCourseService.append(placeInCourse, userId)));
    }

    @PatchMapping("/{placeInCourseId}")
    public ResponseEntity<PlaceInCourseResponse> updatePlaceInCourse(@RequestHeader("X-User-Uid") String userId,
                                                                     @PathVariable Long courseId,
                                                                     @PathVariable Long placeInCourseId,
                                                                     @RequestBody @Valid PlaceInCourseUpdateRequest request) {
        PlaceInCourse placeInCourse = request.toPlaceInCourse();
        placeInCourse.setId(placeInCourseId);
        placeInCourse.setCourseId(courseId);

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

    @GetMapping("/{placeInCourseId}")
    public ResponseEntity<PlaceInCourseResponse> getPlaceInCourse(@RequestHeader("X-User-Uid") String userId,
                                                                  @PathVariable Long courseId,
                                                                  @PathVariable Long placeInCourseId) {
        return ResponseEntity.ok(
                PlaceInCourseResponse.from(
                        placeInCourseService.getById(placeInCourseId, courseId, userId)));
    }
}
