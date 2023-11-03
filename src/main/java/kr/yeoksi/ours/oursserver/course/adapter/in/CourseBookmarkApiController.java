package kr.yeoksi.ours.oursserver.course.adapter.in;

import jakarta.validation.Valid;
import kr.yeoksi.ours.oursserver.course.adapter.in.request.CourseBookmarkCreateRequest;
import kr.yeoksi.ours.oursserver.course.adapter.in.response.CourseBookmarkResponse;
import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import kr.yeoksi.ours.oursserver.course.exception.NoPermissionOfBookmarkException;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class CourseBookmarkApiController {

    private final CourseBookmarkService courseBookmarkService;


    @PostMapping("/bookmarks/{userId}/course-bookmarks")
    public ResponseEntity<CourseBookmarkResponse> createCourseBookmark(@RequestHeader("X-User-Uid") String requestedUserId,
                                                                       @PathVariable String userId,
                                                                       @RequestBody @Valid CourseBookmarkCreateRequest request) {
        if (!requestedUserId.equals(userId)) {
            throw new NoPermissionOfBookmarkException();
        }

        return ResponseEntity.ok(
                CourseBookmarkResponse.from(
                        courseBookmarkService.createCourseBookmark(request.toCourseBookmark(), userId)));
    }

}
