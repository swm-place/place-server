package kr.yeoksi.ours.oursserver.course.domain;

import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CourseBookmark {

    private Long id;
    private User user;
    private String title;
    private LocalDateTime createdAt;
    private List<CourseInBookmark> coursesInBookmark = new ArrayList<>();

}
