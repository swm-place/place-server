package kr.yeoksi.ours.oursserver.course.adapter.in;

import kr.yeoksi.ours.oursserver.course.exception.NoPermissionOfBookmarkException;
import kr.yeoksi.ours.oursserver.course.service.port.in.CourseInBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


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
}
