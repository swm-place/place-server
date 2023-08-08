package kr.yeoksi.ours.oursserver.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.domain.PlaceReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlaceReviewRepository {

    private final EntityManager em;

    /**
     * 한줄 평 저장하기.
     */
    public void save(PlaceReview placeReview) {

        em.persist(placeReview);
    }

    /**
     * 공간에 매핑된 모든 한줄평 조회하기
     */
    public List<PlaceReview> findAllByPlaceId(Long placeId) {

        return em.createQuery(
                "SELECT r FROM PlaceReview r " +
                        "WHERE r.place.id =: placeId", PlaceReview.class)
                .setParameter("placeId", placeId)
                .getResultList();
    }

    /**
     * 공간에 매핑된 한줄평을 주어진 개수만큼 조회하기
     */
    public List<PlaceReview> findByPlaceId(Long placeId, int reviewCount) {

        return em.createQuery(
                "SELECT r FROM PlaceReview  r " +
                        "WHERE r.place.id =: placeId", PlaceReview.class)
                .setParameter("placeId", placeId)
                .setMaxResults(reviewCount)
                .getResultList();
    }
}
