package kr.yeoksi.ours.oursserver.course.adapter.in;

import jakarta.validation.Valid;
import kr.yeoksi.ours.oursserver.course.adapter.in.request.CreateCourseRequest;
import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseApiController {

    private final CourseService courseService;


    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestHeader("X-User-Uid") String userId,  // TODO: Spring Security 이용해 유저 정보 연동
                                         @RequestBody @Valid CreateCourseRequest request) {
        Course course = request.toCourse();
        return ResponseEntity.ok(courseService.create(course, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@RequestHeader("X-User-Uid") String userId,
                                            @PathVariable Long id) {
        return courseService.findById(id, userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Course>> getMyCourses(@RequestHeader("X-User-Uid") String userId) {
        return ResponseEntity.ok(courseService.findAllByUserId(userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@RequestHeader("X-User-Uid") String userId,
                                               @PathVariable Long id,
                                               @RequestBody @Valid Course course) {
        return courseService.findById(course.getId(), userId)
                .map(course1 -> ResponseEntity.ok(courseService.update(course, userId)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@RequestHeader("X-User-Uid") String userId,
                                               @PathVariable Long id) {
        return courseService.findById(id, userId)
                .map(course -> {
                    courseService.delete(id, userId);
                    return ResponseEntity.ok(course);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
