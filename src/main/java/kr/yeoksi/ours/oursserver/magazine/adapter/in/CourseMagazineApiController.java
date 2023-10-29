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
        // TODO: 리스트에 맞는 필드만 제공하도록 수정
        // TODO: 타이틀 사진 혹은 플레이스 사진 연동 (또는 연동을 위한 가이드/레퍼런스 제공)
        return ResponseEntity.ok(
                courseMagazineService.findLatestCourseMagazines(count, page)
                        .stream().map(CourseMagazineResponse::from)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseMagazineResponse> getMagazine(@PathVariable Long id) {
        return ResponseEntity.ok(CourseMagazineResponse.from(courseMagazineService.getById(id)));
    }

}
