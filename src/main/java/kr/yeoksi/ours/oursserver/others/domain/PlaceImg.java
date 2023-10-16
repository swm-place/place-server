package kr.yeoksi.ours.oursserver.others.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.generator.EventType;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class PlaceImg {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_img_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_index")
    private Place place;

    @NotBlank
    @Column(name = "img_url")
    private String imgUrl;

    @Generated(event = EventType.INSERT)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
