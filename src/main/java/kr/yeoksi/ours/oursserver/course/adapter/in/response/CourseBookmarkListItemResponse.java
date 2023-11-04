package kr.yeoksi.ours.oursserver.course.adapter.in.response;

import kr.yeoksi.ours.oursserver.course.domain.CourseBookmark;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class CourseBookmarkListItemResponse {

    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private String imgUrl;


    public static CourseBookmarkListItemResponse from(CourseBookmark courseBookmark) {
        CourseBookmarkListItemResponse response = CourseBookmarkListItemResponse.builder()
                .id(courseBookmark.getId())
                .title(courseBookmark.getTitle())
                .createdAt(courseBookmark.getCreatedAt())
                .build();

        if (!courseBookmark.getCoursesInBookmark().isEmpty()
                && !courseBookmark.getCoursesInBookmark().get(0).getCourse().getPlacesInCourse().isEmpty()) {

            response.setImgUrl(
                    courseBookmark.getCoursesInBookmark().get(0)
                            .getCourse().getPlacesInCourse().get(0)
                            .getPlace().getImgUrl());
        }

        return response;
    }
}
