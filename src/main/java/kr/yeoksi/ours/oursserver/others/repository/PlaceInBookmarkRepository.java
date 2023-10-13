package kr.yeoksi.ours.oursserver.others.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.others.domain.PlaceInBookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceInBookmarkRepository {

    private final EntityManager em;

    /**
     * 장소 북마크 리스트에 장소 북마크 저장
     */
    public void save(PlaceInBookmark placeInBookmark) {

        em.persist(placeInBookmark);
    }

    public Optional<PlaceInBookmark> findByIds(String placeId, Long placeBookmarkId) {

        List<PlaceInBookmark> placeInBookmark = em.createQuery(
                        "SELECT pib FROM PlaceInBookmark pib " +
                                "WHERE pib.place.id =: placeId " +
                                "AND pib.placeBookmark.id =: placeBookmarkId ", PlaceInBookmark.class)
                .setParameter("placeId", placeId)
                .setParameter("placeBookmarkId", placeBookmarkId)
                .getResultList();

        return placeInBookmark.stream().findAny();
    }


    /**
     * 유저의 장소 북마크 여부 확인
     */
    public Optional<PlaceInBookmark> checkBookmark(String userId, String placeId) {

        List<PlaceInBookmark> placeInBookmark = em.createQuery(
                "SELECT pib FROM PlaceInBookmark pib " +
                        "JOIN pib.placeBookmark pb " +
                        "WHERE pb.user.id =: userId AND pib.place.id =: placeId", PlaceInBookmark.class)
                .setParameter("userId", userId)
                .setParameter("placeId", placeId)
                .getResultList();

        return placeInBookmark.stream().findAny();
    }

    /**
     * 장소 북마크 취소하기.
     */
    public void delete(PlaceInBookmark placeInBookmark) {

        em.remove(placeInBookmark);
    }
}
