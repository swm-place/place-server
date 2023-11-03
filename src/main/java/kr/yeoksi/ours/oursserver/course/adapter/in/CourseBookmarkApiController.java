package kr.yeoksi.ours.oursserver.course.adapter.in;

import jakarta.validation.Valid;
import kr.yeoksi.ours.oursserver.course.adapter.in.request.CourseBookmarkCreateRequest;
import kr.yeoksi.ours.oursserver.course.adapter.in.request.CourseBookmarkUpdateRequest;
import kr.yeoksi.ours.oursserver.course.adapter.in.response.CourseBookmarkResponse;
import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import kr.yeoksi.ours.oursserver.course.exception.NoPermissionOfBookmarkException;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/bookmarks/{userId}/course-bookmarks")
    public ResponseEntity<List<CourseBookmarkResponse>> getMyCourseBookmarks(@RequestHeader("X-User-Uid") String requestedUserId,
                                                                             @PathVariable String userId,
                                                                             @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int size) {
        if (!requestedUserId.equals(userId)) {
            throw new NoPermissionOfBookmarkException();
        }

        return ResponseEntity.ok(
                courseBookmarkService.getMyCourseBookmarks(userId, page, size).stream()
                        .map(CourseBookmarkResponse::from)
                        .toList()
        );
    }

    @GetMapping("/bookmarks/{userId}/course-bookmarks/{courseBookmarkId}")
    public ResponseEntity<CourseBookmarkResponse> getCourseBookmark(@RequestHeader("X-User-Uid") String requestedUserId,
                                                                     @PathVariable String userId,
                                                                     @PathVariable Long courseBookmarkId) {
        if (!requestedUserId.equals(userId)) {
            throw new NoPermissionOfBookmarkException();
        }

        return ResponseEntity.ok(
                CourseBookmarkResponse.from(
                        courseBookmarkService.getCourseBookmark(courseBookmarkId, userId)));
    }

    @PatchMapping("/bookmarks/{userId}/course-bookmarks/{courseBookmarkId}")
    public ResponseEntity<CourseBookmarkResponse> updateCourseBookmark(@RequestHeader("X-User-Uid") String requestedUserId,
                                                                        @PathVariable String userId,
                                                                        @PathVariable Long courseBookmarkId,
                                                                        @RequestBody @Valid CourseBookmarkUpdateRequest request) {
        if (!requestedUserId.equals(userId)) {
            throw new NoPermissionOfBookmarkException();
        }

        CourseBookmark courseBookmark = request.toCourseBookmark();
        courseBookmark.setId(courseBookmarkId);

        return ResponseEntity.ok(
                CourseBookmarkResponse.from(
                        courseBookmarkService.updateCourseBookmark(courseBookmark, userId)));
    }

}
