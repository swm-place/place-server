package kr.yeoksi.ours.oursserver.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class TermAgreement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_agreement_index")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_index")
    private User user;

    @ManyToOne
    @JoinColumn(name = "term_index")
    private TermsOfService termsOfService;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public TermAgreement(User user, TermsOfService terms) {
        this.user = user;
        this.termsOfService = terms;
    }
}
