package kr.yeoksi.ours.oursserver.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.domain.Place;
import kr.yeoksi.ours.oursserver.domain.PlaceBookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceBookmarkRepository {

    private final EntityManager em;

    /**
     * 유저의 북마크 그룹 생성하기.
     */
    public void save(PlaceBookmark placeBookmark) {

        em.persist(placeBookmark);
    }

    /**
     * 공간 북마크 그룹 아이디로 조회하기.
     */
    public Optional<PlaceBookmark> findById(Long id) {

        PlaceBookmark placeBookmark = em.find(PlaceBookmark.class, id);
        return Optional.ofNullable(placeBookmark);
    }

    /**
     * 유저 아이디와 공간 아이디로 유저의 북마크 여부 조회하기
     */
    /*
    public Optional<PlaceBookmark> findByIds(String userId, Long placeId) {

        List<PlaceBookmark> placeBookmark =  em.createQuery(
                "SELECT b FROM PlaceBookmark b " +
                        "WHERE b.user.id =: userId " +
                        "AND b.place.id =: placeId ", PlaceBookmark.class)
                .setParameter("userId", userId)
                .setParameter("placeId", placeId)
                .getResultList();

        return placeBookmark.stream().findAny();
    }

     */

    /**
     * 공간 북마크 그룹 삭제하기
     */
    public void delete(PlaceBookmark placeBookmark) {

        em.remove(placeBookmark);
    }

    /**
     * 유저가 북마크한 공간 리스트 조회하기
     */
    /*
    public List<PlaceBookmark> findAllBookmarkedPlace(String userId) {

        return em.createQuery(
                "SELECT DISTINCT b FROM User u " +
                        "JOIN u.placeBookmarks b " +
                        "JOIN FETCH b.place p " +
                        "WHERE u.id =: userId " +
                        "ORDER BY b.id", PlaceBookmark.class)
                .setParameter("userId", userId)
                .getResultList();
    }

     */
}
