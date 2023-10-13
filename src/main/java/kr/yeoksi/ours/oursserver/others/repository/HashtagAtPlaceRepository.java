package kr.yeoksi.ours.oursserver.others.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.others.domain.Hashtag;
import kr.yeoksi.ours.oursserver.others.domain.HashtagAtPlace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HashtagAtPlaceRepository {

    private final EntityManager em;

    /**
     * 공간에 해시태그가 매핑된 정보 저장하기.
     */
    public void save(HashtagAtPlace hashtagAtPlace) {
        em.persist(hashtagAtPlace);
    }

    /**
     * 공간에 매핑된 모든 해시태그 조회하기.
     */
    public List<Hashtag> findAllHashtagsMapping(String placeId) {

        List<Hashtag> hashtagList = new ArrayList<>();
        List<HashtagAtPlace> hashtagAtPlaces =  em.createQuery(
                "SELECT hap FROM HashtagAtPlace hap " +
                        "JOIN FETCH hap.hashtag h " +
                        "WHERE hap.place.id =: placeId ", HashtagAtPlace.class)
                .setParameter("placeId", placeId)
                .getResultList();

        if(!CollectionUtils.isEmpty(hashtagAtPlaces)) {
            for(HashtagAtPlace hashtagAtPlace: hashtagAtPlaces) {
                hashtagList.add(hashtagAtPlace.getHashtag());
            }
        }

        return hashtagList;
    }
}
