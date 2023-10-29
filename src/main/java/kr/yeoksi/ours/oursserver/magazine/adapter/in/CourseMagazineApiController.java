package kr.yeoksi.ours.oursserver.magazine.adapter.in;

import kr.yeoksi.ours.oursserver.magazine.adapter.in.response.CourseMagazineResponse;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/magazine/courses")
@RequiredArgsConstructor
public class CourseMagazineApiController {
    CourseMagazineService courseMagazineService;

    @GetMapping
    public ResponseEntity<List<CourseMagazineResponse>> getLatestMagazines(@RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "10") int count) {
        return ResponseEntity.ok(
                courseMagazineService.findLatestCourseMagazines(count, page)
                        .stream().map(CourseMagazineResponse::from)
                        .toList());
    }

}
