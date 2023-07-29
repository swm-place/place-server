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
     * placeId로 해당 공간에 매핑된 이미지 리스트 조회하기.
     */
    public List<PlaceImg> findByPlaceId(Long placeId) {

        return em.createQuery(
                "SELECT i FROM PlaceImg  i " +
                        "WHERE i.place.id =: placeId ", PlaceImg.class)
                .setParameter("placeId", placeId)
                .getResultList();
    }
}
