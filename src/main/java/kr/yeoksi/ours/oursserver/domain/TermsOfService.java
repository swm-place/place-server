package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class TermsOfService {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_index")
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @NotBlank
    private String type;

    @NotNull
    private Integer version;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
