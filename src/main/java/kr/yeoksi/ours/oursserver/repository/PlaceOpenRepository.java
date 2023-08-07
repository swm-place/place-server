package kr.yeoksi.ours.oursserver.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.domain.PlaceOpen;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlaceOpenRepository {

    private final EntityManager em;

    /**
     * 운영중 정보 저장하기
     */
    public void save(PlaceOpen placeOpen) {

        em.persist(placeOpen);
    }

    /**
     * 해당 공간이 현재 운영중이라고 응답한 유저의 수 조회하기.
     */
    public int countOpen(Long id) {

        return em.createQuery(
                "SELECT o FROM PlaceOpen o " +
                        "WHERE o.place.id =: id", PlaceOpen.class)
                .setParameter("id", id)
                .getResultList()
                .size();
    }
}
