package kr.yeoksi.ours.oursserver.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.domain.Hashtag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HashtagRepository {

    private final EntityManager em;

    /**
     * 해시태그 저장하기.
     */
    public void save(Hashtag hashtag) {
        em.persist(hashtag);
    }

    /**
     * 해시태그 아이디로 해시태그 하나 조회하기.
     */
    public Hashtag findById(Long id) {
        return em.find(Hashtag.class, id);
    }
}
