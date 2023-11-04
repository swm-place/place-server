package kr.yeoksi.ours.oursserver.course.adapter.in;

import kr.yeoksi.ours.oursserver.course.exception.NoPermissionOfBookmarkException;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseInBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class CourseInBookmarkApiController {

    private final CourseInBookmarkService courseInBookmarkService;


    @PostMapping("/bookmarks/{userId}/course-bookmarks/{courseBookmarkId}/courses/{courseId}")
    public void addCourseToBookmark(@RequestHeader("X-User-Uid") String requestedUserId,
                                    @PathVariable String userId,
                                    @PathVariable Long courseBookmarkId,
                                    @PathVariable Long courseId) {
        if (!requestedUserId.equals(userId)) {
            throw new NoPermissionOfBookmarkException();
        }

        courseInBookmarkService.addCourseToBookmark(courseBookmarkId, courseId, userId);
    }

    @DeleteMapping("/bookmarks/{userId}/course-bookmarks/{courseBookmarkId}/courses/{courseId}")
    public void deleteCourseInBookmark(@RequestHeader("X-User-Uid") String requestedUserId,
                                       @PathVariable String userId,
                                       @PathVariable Long courseBookmarkId,
                                       @PathVariable Long courseId) {
        if (!requestedUserId.equals(userId)) {
            throw new NoPermissionOfBookmarkException();
        }

        courseInBookmarkService.deleteCourseInBookmark(courseBookmarkId, courseId, userId);
    }
}
