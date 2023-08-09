package kr.yeoksi.ours.oursserver.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceRepository {

    private final EntityManager em;

    /**
     * 공간 저장하기.
     */
    public Long save(Place place) {
        em.persist(place);
        return place.getId();
    }

    /**
     * id로 공간 조회하기.
     */
    public Optional<Place> findById(Long id) {
        Place place = em.find(Place.class, id);
        return Optional.ofNullable(place);
    }
}
