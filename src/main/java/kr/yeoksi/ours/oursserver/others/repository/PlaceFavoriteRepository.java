package kr.yeoksi.ours.oursserver.others.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.others.domain.PlaceFavorite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceFavoriteRepository {

    private final EntityManager em;

    /**
     * 좋아요 정보 저장하기.
     */
    public void save(PlaceFavorite placeFavorite) {

        em.persist(placeFavorite);
    }

    /**
     * 공간의 좋아요 개수 조회하기.
     */
    public int countFavorite(String id) {

        return em.createQuery(
                "SELECT f FROM PlaceFavorite f " +
                        "WHERE f.place.id =: id", PlaceFavorite.class)
                .setParameter("id", id)
                .getResultList()
                .size();
    }

    /**
     * 유저 아이디와 공간 아이디로 유저의 좋아요 여부 조회하기
     */
    public Optional<PlaceFavorite> findByIds(String userId, String placeId) {

        List<PlaceFavorite> placeFavorite = em.createQuery(
                "SELECT f FROM PlaceFavorite f " +
                        "WHERE f.user.id =: userId " +
                        "AND f.place.id =: placeId ", PlaceFavorite.class)
                .setParameter("userId", userId)
                .setParameter("placeId", placeId)
                .getResultList();

        return placeFavorite.stream().findAny();
    }

    /**
     * 좋아요 삭제하기
     */
    public void delete(PlaceFavorite placeFavorite) {

        em.remove(placeFavorite);
    }
}
