package kr.yeoksi.ours.oursserver.magazine.adapter.in;

import kr.yeoksi.ours.oursserver.magazine.adapter.in.request.CourseMagazineRequest;
import kr.yeoksi.ours.oursserver.magazine.adapter.in.response.CourseMagazineListItemResponse;
import kr.yeoksi.ours.oursserver.magazine.adapter.in.response.CourseMagazineResponse;
import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course-magazines")
@RequiredArgsConstructor
public class CourseMagazineApiController {

    private final CourseMagazineService courseMagazineService;


    @GetMapping
    public ResponseEntity<List<CourseMagazineListItemResponse>> getLatestMagazines(@RequestParam(defaultValue = "0") int page,
                                                                                   @RequestParam(defaultValue = "10") int count) {
        return ResponseEntity.ok(
                courseMagazineService.findLatestCourseMagazines(count, page)
                        .stream().map(CourseMagazineListItemResponse::from)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseMagazineResponse> getMagazine(@RequestHeader(value = "X-User-Uid", required = false) String userId,
                                                              @PathVariable Long id) {
        return ResponseEntity.ok(CourseMagazineResponse.from(courseMagazineService.getById(id, userId)));
    }

    @PostMapping
    public ResponseEntity<CourseMagazineResponse> publishMagazine(@RequestHeader("X-User-Uid") String userId,
                                                                  @RequestBody CourseMagazineRequest request) {
        return ResponseEntity.ok(CourseMagazineResponse.from(
                courseMagazineService.publish(request.toCourseMagazine(), userId)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CourseMagazineResponse> updateMagazine(@RequestHeader("X-User-Uid") String userId,
                                                                 @RequestBody CourseMagazineRequest request,
                                                                 @PathVariable Long id) {
        CourseMagazine courseMagazine = request.toCourseMagazine();
        courseMagazine.setId(id);

        return ResponseEntity.ok(CourseMagazineResponse.from(
                courseMagazineService.update(courseMagazine, userId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMagazine(@RequestHeader("X-User-Uid") String userId,
                                               @PathVariable Long id) {
        courseMagazineService.delete(id, userId);
        return ResponseEntity.ok().build();
    }

}
