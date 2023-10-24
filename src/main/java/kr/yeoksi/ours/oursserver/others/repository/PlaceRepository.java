package kr.yeoksi.ours.oursserver.others.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.others.domain.Place;
import kr.yeoksi.ours.oursserver.others.domain.PlaceInBookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceRepository {

    private final EntityManager em;

    /**
     * 공간 저장하기.
     */
    public String save(Place place) {

        em.persist(place);
        return place.getId();
    }

    /**
     * id로 공간 조회하기.
     */
    public Optional<Place> findById(String id) {

        Place place = em.find(Place.class, id);
        return Optional.ofNullable(place);
    }

    /**
     * 엘라스틱 id로 공간 조회하기
     * Elastic ID가 곧 ID 이므로, findById()로 대체
     */
//    public Optional<Place> findByElasticId(String elasticId) {
//
//        return em.createQuery(
//                "SELECT p FROM Place p " +
//                        "WHERE p.elasticId =: elasticId ", Place.class)
//                .setParameter("elasticId", elasticId)
//                .getResultList()
//                .stream().findAny();
//    }

    /**
     * 유저의 장소 북마크 그룹 내의 장소들 조회하기
     */
    public List<Place> readAllPlaceInPlaceBookmark(String userId, Long placeBookmarkId) {

        List<Place> placesInBookmark = em.createQuery(
                        "SELECT p FROM PlaceInBookmark pib " +
                                "JOIN FETCH pib.place p " +
                                "JOIN pib.placeBookmark pb " +
                                "WHERE pb.user.id =: userId " +
                                "AND pb.id =: placeBookmarkId ", Place.class)
                .setParameter("userId", userId)
                .setParameter("placeBookmarkId", placeBookmarkId)
                .getResultList();

        return placesInBookmark;
    }
}
