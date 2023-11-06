package kr.yeoksi.ours.oursserver.course.adapter.in;

import jakarta.validation.Valid;
import kr.yeoksi.ours.oursserver.course.adapter.in.request.CreateCourseRequest;
import kr.yeoksi.ours.oursserver.course.adapter.in.request.UpdateCourseRequest;
import kr.yeoksi.ours.oursserver.course.adapter.in.response.CourseResponse;
import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// TODO: Spring Security 이용해 유저 정보 연동


@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseApiController {

    private final CourseService courseService;


    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestHeader("X-User-Uid") String userId,
                                                       @RequestBody @Valid CreateCourseRequest request) {
        Course course = request.toCourse();
        return ResponseEntity.ok(
                CourseResponse.from(
                        courseService.create(course, userId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourse(@RequestHeader("X-User-Uid") String userId,
                                            @PathVariable Long id) {
        return courseService.findById(id, userId)
                .map(CourseResponse::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getMyCourses(@RequestHeader("X-User-Uid") String userId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(
                courseService.findAllByUserId(userId, page, size).stream()
                        .map(CourseResponse::from)
                        .toList()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@RequestHeader("X-User-Uid") String userId,
                                               @PathVariable Long id,
                                               @RequestBody @Valid UpdateCourseRequest request) {
        Course course = request.toCourse();
        course.setId(id);
        return ResponseEntity.ok(
                CourseResponse.from(
                        courseService.update(course, userId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@RequestHeader("X-User-Uid") String userId,
                                               @PathVariable Long id) {
        courseService.delete(id, userId);
        return ResponseEntity.ok().build();
    }

}
