package kr.yeoksi.ours.oursserver.others.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.others.domain.PlaceInBookmark;
import kr.yeoksi.ours.oursserver.others.jpa.repository.PlaceInBookmarkJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceInBookmarkRepository {

    private final EntityManager em;
    private final PlaceInBookmarkJpaRepository placeInBookmarkJpaRepository;

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

    public List<PlaceInBookmark> findAllByPlaceBookmarkId(Long placeBookmarkId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return placeInBookmarkJpaRepository.findAllByPlaceBookmark_Id(placeBookmarkId, pageRequest).stream()
                .toList();
    }

    /**
     * 장소 북마크 취소하기.
     */
    public void delete(PlaceInBookmark placeInBookmark) {

        em.remove(placeInBookmark);
    }
}
