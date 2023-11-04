package kr.yeoksi.ours.oursserver.course.adapter.in.response;

import kr.yeoksi.ours.oursserver.course.domain.Course;
import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import kr.yeoksi.ours.oursserver.course.domain.PlaceInCourse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class CourseResponse {
    private Long id;

    private String title;
    private String description;

    @Builder.Default
    private List<PlaceInCourseResponse> placesInCourse = new ArrayList<>();

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private boolean inProgress;
    private boolean isFinished;

    private LocalDateTime createdAt;

    @Builder.Default
    private List<CourseBookmarkReferenceResponse> bookmarks = new ArrayList<>();


    public static CourseResponse from(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .placesInCourse(course.getPlacesInCourse().stream()
                        .map(PlaceInCourseResponse::from)
                        .toList())
                .startAt(course.getStartAt())
                .endAt(course.getEndAt())
                .inProgress(course.isInProgress())
                .isFinished(course.isFinished())
                .createdAt(course.getCreatedAt())
                .bookmarks(course.getBookmarks().stream()
                        .map(CourseBookmarkReferenceResponse::from)
                        .toList())
                .build();
    }
}
