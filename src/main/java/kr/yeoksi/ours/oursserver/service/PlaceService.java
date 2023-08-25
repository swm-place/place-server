package kr.yeoksi.ours.oursserver.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import kr.yeoksi.ours.oursserver.domain.*;
import kr.yeoksi.ours.oursserver.domain.dto.place.request.ReadPlaceFromElastic;
import kr.yeoksi.ours.oursserver.exception.ErrorCode;
import kr.yeoksi.ours.oursserver.exception.NotExistedPlaceException;
import kr.yeoksi.ours.oursserver.exception.NotFoundPlaceAtElasticSearchException;
import kr.yeoksi.ours.oursserver.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceImgRepository placeImgRepository;
    private final HashtagAtPlaceRepository hashtagAtPlaceRepository;
    private final PlaceFavoriteRepository placeFavoriteRepository;
    private final PlaceOpenRepository placeOpenRepository;
    private final PlaceReviewRepository placeReviewRepository;
    private final PlaceReviewFavoriteRepository placeReviewFavoriteRepository;

    // Create the API client
    private final ElasticsearchClient elasticsearchClient;

    /**
     * 엘라스틱에서 장소 정보 조회하기
     */
    public ReadPlaceFromElastic readPlaceFromElastic(String placeId) throws Exception {

        GetResponse<ObjectNode> response = elasticsearchClient.get(g -> g
                        .index("place")
                        .id(placeId),
                ObjectNode.class
        );

        if(!response.found()) {
            throw new NotFoundPlaceAtElasticSearchException(ErrorCode.NOT_FOUND_PLACE_AT_ELASTIC_SEARCH);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode json = response.source();
        ReadPlaceFromElastic readResult = new ReadPlaceFromElastic(
                json.get("name").asText(),
                objectMapper.treeToValue(json.get("hashtags"), List.class),
                json.get("summary").asText(),
                json.get("road_address").asText(),
                json.get("category").asText()
        );

        return readResult;
    }

    /**
     * id로 장소 조회하기.
     */
    public Place findById(Long id) {

        Optional<Place> place = placeRepository.findById(id);
        if(!place.isPresent()) throw new NotExistedPlaceException(ErrorCode.NOT_EXISTED_PLACE);

        return place.get();
    }

    /**
     * 엘라스틱 id로 장소 조회하기
     */
    public Place findByElasticId(String elasticId) {

        Optional<Place> place = placeRepository.findByElasticId(elasticId);
        if(!place.isPresent()) throw new NotExistedPlaceException(ErrorCode.NOT_EXISTED_PLACE);

        return place.get();
    }











    ///////////////////////////////////////////

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
     * 공간에 매핑된 모든 해시태그들을 조회하기.
     */
    public List<String> getHashtagList(Long id) {

        List<Hashtag> hashtagList = hashtagAtPlaceRepository.findAllHashtagsMapping(id);

        List<String> hashtagNameList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(hashtagList)) {
            for(Hashtag hashtag : hashtagList) {
                hashtagNameList.add(hashtag.getName());
            }
        }

        return hashtagNameList;
    }

    /**
     * 공간의 좋아요 개수 조회하기.
     */
    public int getFavoriteCount(Long id) {

        return placeFavoriteRepository.countFavorite(id);
    }


    /**
     * 현재 운영중이라고 응답한 유저의 수 조회하기.
     */
    public int getOpenCount(Long id) {

        return placeOpenRepository.countOpen(id);
    }

    /**
     * 해당 공간에 대한 유저의 운영중 응답 여부 확인하기.
     */
    public boolean checkOpen(String userId, Long placeId) {

        Optional<PlaceOpen> placeOpen = placeOpenRepository.findByIds(userId, placeId);
        if(!placeOpen.isPresent()) return false;
        else return true;
    }

    /**
     * 해당 공간에 매핑된 한줄평들 조회하기.
     */
    public List<PlaceReview> getAllPlaceReviewList(Long id) {

        return placeReviewRepository.findAllByPlaceId(id);
    }

    /**
     * 해당 공간에 매핑된 한줄평을 주어진 개수만큼 조회하기
     */
    public List<PlaceReview> getPlaceReviewList(Long id, int reviewCount) {

        return placeReviewRepository.findByPlaceId(id, reviewCount);
    }

    /**
     * 한줄평에 대한 좋아요 여부 확인하기
     */
    public boolean checkReviewFavorite(String userId, Long placeReviewId) {

        Optional<PlaceReviewFavorite> placeReviewFavorite = placeReviewFavoriteRepository.findByIds(userId, placeReviewId);
        if(!placeReviewFavorite.isPresent()) return false;
        else return true;
    }
}
