package kr.yeoksi.ours.oursserver.course.domain;

import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseBookmark {

    private Long id;
    private User user;
    private String title;
    private LocalDateTime createdAt;

    @Builder.Default
    private List<CourseInBookmark> coursesInBookmark = new ArrayList<>();

}
