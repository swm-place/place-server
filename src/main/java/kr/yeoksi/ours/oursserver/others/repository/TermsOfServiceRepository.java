package kr.yeoksi.ours.oursserver.others.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.others.domain.TermsOfService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TermsOfServiceRepository {

    private final EntityManager em;

    /**
     * 이용약관 저장하기.
     */
    public void save(TermsOfService term) {
        em.persist(term);
    }

    /**
     * id로 이용약관 조회하기.
     */
    public TermsOfService findById(Long id) {
        return em.find(TermsOfService.class, id);
    }

    /**
     * 모든 이용약관 리스트 조회하기
     */
    public List<TermsOfService> readAllTerms() {
        return em.createQuery(
                "SELECT t " +
                        "FROM TermsOfService t ", TermsOfService.class)
                .getResultList();
    }
}
