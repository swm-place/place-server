package kr.yeoksi.ours.oursserver.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.domain.PlaceImg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlaceImgRepository {

    private final EntityManager em;

    /**
     * 공간 이미지 저장하기.
     */
    public void save(PlaceImg placeImg) {
        em.persist(placeImg);
    }

    /**
     * placeId로 해당 공간에 매핑된 이미지 리스트 조회하기.
     */
    public List<PlaceImg> findByPlaceId(Long placeId, int imgCount) {

        return em.createQuery(
                "SELECT i FROM PlaceImg  i " +
                        "WHERE i.place.id =: placeId " +
                        "ORDER BY i.id DESC ", PlaceImg.class)
                .setParameter("placeId", placeId)
                .setMaxResults(imgCount)
                .getResultList();
    }

    /**
     * placeId로 해당 공간에 매핑된 이미지 리스트 조회하기.
     */
    public List<PlaceImg> findAllByPlaceId(Long placeId) {

        return em.createQuery(
                        "SELECT i FROM PlaceImg  i " +
                                "WHERE i.place.id =: placeId ", PlaceImg.class)
                .setParameter("placeId", placeId)
                .getResultList();
    }
}
