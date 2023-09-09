package kr.yeoksi.ours.oursserver.others.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.others.domain.PlaceOpen;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    /**
     * 유저 아이디와 공간 아이디로 유저의 운영중 응답 여부 조회하기.
     */
    public Optional<PlaceOpen> findByIds(String userId, Long placeId) {

        List<PlaceOpen> placeOpen = em.createQuery(
                "SELECT o FROM PlaceOpen o " +
                        "WHERE o.user.id =: userId " +
                        "AND o.place.id =: placeId ", PlaceOpen.class)
                .setParameter("userId", userId)
                .setParameter("placeId", placeId)
                .getResultList();

        return placeOpen.stream().findAny();
    }
}
