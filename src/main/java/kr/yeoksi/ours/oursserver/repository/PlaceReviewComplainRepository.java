package kr.yeoksi.ours.oursserver.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.domain.PlaceReviewComplain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceReviewComplainRepository {

    private final EntityManager em;

    /**
     * 한줄평 신고 정보 저장하기
     */
    public void save(PlaceReviewComplain placeReviewComplain) {

        em.persist(placeReviewComplain);
    }

    /**
     * 유저 아이디와 한줄평 아이디로 유저의 한줄평 신고 여부 조회하기
     */
    public Optional<PlaceReviewComplain> findByIds(String userId, Long placeReviewId) {

        List<PlaceReviewComplain> placeReviewComplain = em.createQuery(
                "SELECT c FROM PlaceReviewComplain  c " +
                        "WHERE c.user.id =: userId " +
                        "AND c.placeReview.id =: placeReviewId", PlaceReviewComplain.class)
                .setParameter("userId", userId)
                .setParameter("placeReviewId", placeReviewId)
                .getResultList();

        return placeReviewComplain.stream().findAny();
    }
}
