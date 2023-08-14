package kr.yeoksi.ours.oursserver.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.domain.Place;
import kr.yeoksi.ours.oursserver.domain.PlacesInBookmark;
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
    public void save(PlacesInBookmark placesInBookmark) {

        em.persist(placesInBookmark);
    }

    public Optional<PlacesInBookmark> findByIds(Long placeId, Long placeBookmarkId) {

        List<PlacesInBookmark> placesInBookmark = em.createQuery(
                "SELECT p FROM PlacesInBookmark p " +
                        "WHERE p.place.id =: placeId " +
                        "AND p.placeBookmark.id =: placeBookmarkId ", PlacesInBookmark.class)
                .setParameter("placeId", placeId)
                .setParameter("placeBookmarkId", placeBookmarkId)
                .getResultList();

        return placesInBookmark.stream().findAny();
    }
}
