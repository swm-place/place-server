package kr.yeoksi.ours.oursserver.others.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import kr.yeoksi.ours.oursserver.domain.*;
import kr.yeoksi.ours.oursserver.others.domain.*;
import kr.yeoksi.ours.oursserver.others.domain.dto.place.request.ReadPlaceFromElastic;
import kr.yeoksi.ours.oursserver.others.domain.dto.place.response.ReadPlaceReviewResponse;
import kr.yeoksi.ours.oursserver.exception.ErrorCode;
import kr.yeoksi.ours.oursserver.exception.NotExistedPlaceException;
import kr.yeoksi.ours.oursserver.exception.NotFoundPlaceAtElasticSearchException;
import kr.yeoksi.ours.oursserver.others.repository.*;
import kr.yeoksi.ours.oursserver.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    private final PlaceReviewComplainRepository placeReviewComplainRepository;

    // Create the API client
    private final ElasticsearchClient elasticsearchClient;

    /**
     * 장소 저장하기
     */
    @Transactional
    public void createPlace(Place place) {
        placeRepository.save(place);
    }

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
     * 해당 장소에 매핑된 한줄평을 주어진 개수만큼 조회하기
     */
    public List<ReadPlaceReviewResponse> getPlaceReviewList(String uesrId, Long placeId, int reviewCount) {

        // 장소에 매핑된 한줄평을 주어진 개수만큼 조회
        List<PlaceReview> placeReviewList = placeReviewRepository.findByPlaceId(placeId, reviewCount);

        List<ReadPlaceReviewResponse> readPlaceReviewResponseList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(placeReviewList)) {
            for(PlaceReview placeReview : placeReviewList) {

                // 각각의 한줄평에 대한 유저의 좋아요 여부 확인
                Optional<PlaceReviewFavorite> placeReviewFavorite = placeReviewFavoriteRepository.findByIds(uesrId, placeReview.getId());
                boolean isFavorite;
                if(!placeReviewFavorite.isPresent()) isFavorite = false;
                else isFavorite = true;

                // 각각의 한줄평에 대한 유저의 신고 여부 확인
                Optional<PlaceReviewComplain> placeReviewComplain = placeReviewComplainRepository.findByIds(uesrId, placeReview.getId());
                boolean isComplain;
                if(!placeReviewComplain.isPresent()) isComplain = false;
                else isComplain = true;

                readPlaceReviewResponseList.add(
                        new ReadPlaceReviewResponse(
                                placeReview.getId(),
                                placeReview.getContents(),
                                placeReview.getUser().getImgUrl(),
                                placeReview.getUser().getNickname(),
                                placeReview.getCratedAt(),
                                isFavorite,
                                isComplain
                        )
                );
            }
        }
        return readPlaceReviewResponseList;
    }

    /**
     * 장소에 매핑된 모든 이미지 url들을 주어진 개수만큼 조회하기
     */
    public List<String> getImgUrlList(Long placeId, int imgCount) {

        List<String> imgUrlList = new ArrayList<>();

        List<PlaceImg> placeImgList = placeImgRepository.findByPlaceId(placeId, imgCount);
        if(!CollectionUtils.isEmpty(placeImgList)) {
            for(PlaceImg placeImg : placeImgList) {
                imgUrlList.add(placeImg.getImgUrl());
            }
        }
        return imgUrlList;
    }

    /**
     * 장소에 매핑된 모든 이미지 url들을 조회하기
     */
    public List<String> getAllImgUrlList(Long id) {

        List<String> imgUrlList = new ArrayList<>();

        List<PlaceImg> placeImgList = placeImgRepository.findAllByPlaceId(id);
        if(!CollectionUtils.isEmpty(placeImgList)) {
            for(PlaceImg placeImg : placeImgList) {
                imgUrlList.add(placeImg.getImgUrl());
            }
        }
        return imgUrlList;
    }
}
