package kr.yeoksi.ours.oursserver.magazine.adapter.in;


import kr.yeoksi.ours.oursserver.magazine.adapter.in.request.PlaceInCourseMagazineRequest;
import kr.yeoksi.ours.oursserver.magazine.adapter.in.response.PlaceInCourseMagazineResponse;
import kr.yeoksi.ours.oursserver.magazine.domain.PlaceInCourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.PlaceInCourseMagazineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course-magazines/{magazineId}/places")
@RequiredArgsConstructor
public class PlaceInCourseMagazineApiController {

    private final PlaceInCourseMagazineService placeInCourseMagazineService;


    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceInCourseMagazineResponse> getPlaceInCourseMagazine(@PathVariable Long courseId,
                                                                                  @PathVariable Long placeId) {
        return ResponseEntity.ok(PlaceInCourseMagazineResponse.from(
                placeInCourseMagazineService.getById(placeId)));
    }

    @PostMapping
    public ResponseEntity<PlaceInCourseMagazineResponse> appendPlaceInCourseMagazine(@RequestHeader("X-User-Uid") String userId,
                                                                                     @PathVariable Long courseId,
                                                                                     @RequestBody PlaceInCourseMagazineRequest request) {
        return ResponseEntity.ok(PlaceInCourseMagazineResponse.from(
                placeInCourseMagazineService.append(request.toPlaceInCourseMagazine(), courseId, userId)));
    }

    @PatchMapping("/{placeId}")
    public ResponseEntity<PlaceInCourseMagazineResponse> updatePlaceInCourseMagazine(@RequestHeader("X-User-Uid") String userId,
                                                                                     @PathVariable Long courseId,
                                                                                     @RequestBody PlaceInCourseMagazineRequest request,
                                                                                     @PathVariable Long placeId) {
        PlaceInCourseMagazine placeInCourseMagazine = request.toPlaceInCourseMagazine();
        placeInCourseMagazine.setId(placeId);

        return ResponseEntity.ok(PlaceInCourseMagazineResponse.from(
                placeInCourseMagazineService.update(placeInCourseMagazine, courseId, userId)));
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity<Void> deletePlaceInCourseMagazine(@RequestHeader("X-User-Uid") String userId,
                                                            @PathVariable Long magazineId,
                                                            @PathVariable Long placeId) {
        placeInCourseMagazineService.delete(placeId, magazineId, userId);
        return ResponseEntity.ok().build();
    }
}
