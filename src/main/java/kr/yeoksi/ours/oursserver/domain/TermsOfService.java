package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
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
    @Column(length = 50)
    @Size(max = 50)
    private String title;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String contents;

    @NotBlank
    @Column(length = 20)
    @Size(max = 20)
    private String type;

    @NotNull
    private int version;

    @NotNull
    @ColumnDefault("true")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean required;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public boolean isRequired() {
        return required;
    }
}
