package kr.yeoksi.ours.oursserver.others.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.others.domain.Place;
import kr.yeoksi.ours.oursserver.others.domain.PlaceBookmark;
import kr.yeoksi.ours.oursserver.others.domain.PlaceInBookmark;
import kr.yeoksi.ours.oursserver.others.domain.dto.place.response.ThumbnailInfoResponse;
import kr.yeoksi.ours.oursserver.others.jpa.repository.PlaceBookmarkJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceBookmarkRepository {

    private final EntityManager em;
    private final PlaceBookmarkJpaRepository placeBookmarkJpaRepository;

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
    public Optional<PlaceBookmark> findByIds(String userId, String placeId) {

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

    /**
     * 유저의 공간 북마크 그룹 리스트 조회하기
     */
    public List<PlaceBookmark> findByUserId(String userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return placeBookmarkJpaRepository.findByUserId(userId, pageRequest).stream()
                .toList();
    }

    public List<PlaceInBookmark> getThumbnailInfo(Long placeBookmarkId) {
        return em.createQuery(
                "SELECT pib FROM PlaceInBookmark pib " +
                        "WHERE pib.placeBookmark.id =: placeBookmarkId " +
                        "ORDER BY pib.id DESC", PlaceInBookmark.class)
                .setParameter("placeBookmarkId", placeBookmarkId)
                .setMaxResults(4)
                .getResultList();


        /*
        return em.createQuery(
                        "SELECT pib FROM PlaceBookmark pb " +
                                "JOIN FETCH pb.placeInBookmarks pib " +
                                "WHERE pb.id =: placeBookmarkId " +
                                "ORDER BY pib.id DESC", PlaceInBookmark.class)
                .setParameter("placeBookmarkId", placeBookmarkId)
                .setMaxResults(4)
                .getResultList();

         */
    }

    /*
    public List<PlaceBookmark> findByUserId(String userId, int atAPage) {
        return em.createQuery(
                "SELECT pb FROM PlaceBookmark pb " +
                        "JOIN FETCH pb.user u " +
                        "LEFT JOIN FETCH pb.placeInBookmarks pib " +
                        "WHERE u.id =: userId " +
                        "ORDER BY pb.id DESC", PlaceBookmark.class)
                .setParameter("userId", userId)
                .setMaxResults(atAPage)
                .getResultList();
    }

    public List<PlaceBookmark> findByUserIdPaging(String userId, Long cursor, int atAPage) {
        return em.createQuery(
                "SELECT pb FROM PlaceBookmark pb " +
                        "JOIN FETCH pb.user u " +
                        "LEFT JOIN FETCH pb.placeInBookmarks pib " +
                        "WHERE u.id =: userId AND pb.id < : cursor " +
                        "ORDER BY pb.id DESC", PlaceBookmark.class)
                .setParameter("userId", userId)
                .setParameter("cursor", cursor)
                .setMaxResults(atAPage)
                .getResultList();
    }
     */
}
