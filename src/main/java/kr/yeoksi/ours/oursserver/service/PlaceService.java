package kr.yeoksi.ours.oursserver.service;

import kr.yeoksi.ours.oursserver.domain.*;
import kr.yeoksi.ours.oursserver.exception.ErrorCode;
import kr.yeoksi.ours.oursserver.exception.NotExistedPlaceException;
import kr.yeoksi.ours.oursserver.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceImgRepository placeImgRepository;
    private final HashtagAtPlaceRepository hashtagAtPlaceRepository;
    private final PlaceBookmarkRepository placeBookmarkRepository;
    private final PlaceFavoriteRepository placeFavoriteRepository;

    /**
     * id로 공간 조회하기.
     */
    public Place findById(Long id) {

        Optional<Place> place = placeRepository.findById(id);
        if(!place.isPresent()) throw new NotExistedPlaceException(ErrorCode.NOT_EXISTED_PLACE);

        return place.get();
    }

    /**
     * 공간에 매핑된 모든 이미지 url들을 조회하기
     */
    public List<String> getImgUrlList(Long id) {

        List<String> imgUrlList = new ArrayList<>();

        List<PlaceImg> placeImgList = placeImgRepository.findByPlaceId(id);
        if(!CollectionUtils.isEmpty(placeImgList)) {
            for(PlaceImg placeImg : placeImgList) {
                imgUrlList.add(placeImg.getImgUrl());
            }
        }
        return imgUrlList;
    }

    /**
     * 공간에 매핑된 모든 해시태그들을 조회하기
     */
    public List<Hashtag> getHashtagList(Long id) {

        return hashtagAtPlaceRepository.findAllHashtagsMapping(id);
    }

    /**
     * 유저가 공간을 북마크했는지 여부를 확인하기
     */
    public boolean checkBookmark(String userid, Long placeId) {

        Optional<PlaceBookmark> placeBookmark = placeBookmarkRepository.findByIds(userid, placeId);
        if(!placeBookmark.isPresent()) return false;
        else return true;
    }

    /**
     *
     */
    public int getFavoriteCount(Long id) {

        return placeFavoriteRepository.countFavorite(id);
    }
}
