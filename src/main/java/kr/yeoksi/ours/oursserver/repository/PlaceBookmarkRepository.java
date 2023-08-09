package kr.yeoksi.ours.oursserver.repository;

import jakarta.persistence.EntityManager;
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
     * 유저의 북마크 정보 저장하기
     */
    public void save(PlaceBookmark placeBookmark) {
        em.persist(placeBookmark);
    }

    /**
     * 유저 아이디와 공간 아이디로 유저의 북마크 여부 조회하기
     */
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

    /**
     * 북마크 삭제하기
     */
    public void delete(PlaceBookmark placeBookmark) {

        em.remove(placeBookmark);
    }
}
