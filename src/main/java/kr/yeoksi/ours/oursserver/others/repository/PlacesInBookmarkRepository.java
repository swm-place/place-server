package kr.yeoksi.ours.oursserver.others.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.others.domain.PlaceInBookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlacesInBookmarkRepository {

    private final EntityManager em;

    /**
     * 공간 북마크하기
     */
    public void save(PlaceInBookmark placeInBookmark) {

        em.persist(placeInBookmark);
    }

    public Optional<PlaceInBookmark> findByIds(Long placeId, Long placeBookmarkId) {

        List<PlaceInBookmark> placeInBookmark = em.createQuery(
                "SELECT p FROM PlaceInBookmark p " +
                        "WHERE p.place.id =: placeId " +
                        "AND p.placeBookmark.id =: placeBookmarkId ", PlaceInBookmark.class)
                .setParameter("placeId", placeId)
                .setParameter("placeBookmarkId", placeBookmarkId)
                .getResultList();

        return placeInBookmark.stream().findAny();
    }
}
