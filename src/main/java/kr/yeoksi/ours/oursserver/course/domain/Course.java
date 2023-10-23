package kr.yeoksi.ours.oursserver.course.domain;

import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    private Long id;
    private User user;

    private String title;
    private String description;

    @Builder.Default
    private List<PlaceInCourse> placesInCourse = new ArrayList<>();

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private boolean inProgress;
    private boolean isFinished;

    private LocalDateTime createdAt;


    public void update(Course source) {
        PropertyDescriptor[] sourcePds = BeanUtils.getPropertyDescriptors(source.getClass());

        List<String> ignoreProperties = new ArrayList<>();

        for (PropertyDescriptor sourcePd : sourcePds) {
            Method readMethod = sourcePd.getReadMethod();
            if (readMethod != null) {
                try {
                    Object value = readMethod.invoke(source);
                    if (value == null) {
                        ignoreProperties.add(sourcePd.getName());
                    }
                } catch (Throwable ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        BeanUtils.copyProperties(source, this, ignoreProperties.toArray(new String[0]));
    }
}
