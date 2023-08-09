package kr.yeoksi.ours.oursserver.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.domain.PlaceReviewFavorite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceReviewFavoriteRepository {

    private final EntityManager em;

    /**
     * 한줄평 좋아요 정보 저장하기
     */
    public void save(PlaceReviewFavorite placeReviewFavorite) {

        em.persist(placeReviewFavorite);
    }

    /**
     * 유저 아이디와 한줄평 아이디로 유저의 한줄평 좋아요 여부 조회하기
     */
    public Optional<PlaceReviewFavorite> findByIds(String userId, Long placeReviewId) {

        List<PlaceReviewFavorite> placeReviewFavorite = em.createQuery(
                "SELECT f FROM PlaceReviewFavorite f " +
                        "WHERE f.user.id =: userId " +
                        "AND f.placeReview.id =: placeReviewId", PlaceReviewFavorite.class)
                .setParameter("userId", userId)
                .setParameter("placeReviewId", placeReviewId)
                .getResultList();

        return placeReviewFavorite.stream().findAny();
    }
}
