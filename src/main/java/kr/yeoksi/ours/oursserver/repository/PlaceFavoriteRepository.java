package kr.yeoksi.ours.oursserver.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.domain.PlaceFavorite;
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
    public int countFavorite(Long id) {

        return em.createQuery(
                "SELECT f FROM PlaceFavorite f " +
                        "WHERE f.place.id =: id", PlaceFavorite.class)
                .setParameter("id", id)
                .getResultList()
                .size();
    }
}
