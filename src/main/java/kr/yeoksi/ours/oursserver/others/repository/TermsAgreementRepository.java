package kr.yeoksi.ours.oursserver.others.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.others.domain.TermAgreement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TermsAgreementRepository {

    private final EntityManager em;

    public void save(TermAgreement agreement) {
        em.persist(agreement);
    }
}
