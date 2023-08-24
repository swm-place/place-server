package kr.yeoksi.ours.oursserver.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.node.ObjectNode;
import kr.yeoksi.ours.oursserver.controller.PlaceApiController;
import kr.yeoksi.ours.oursserver.domain.*;
import kr.yeoksi.ours.oursserver.exception.ErrorCode;
import kr.yeoksi.ours.oursserver.exception.NotExistedPlaceException;
import kr.yeoksi.ours.oursserver.exception.NotFoundPlaceAtElasticSearchException;
import kr.yeoksi.ours.oursserver.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
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

import javax.net.ssl.SSLContext;
import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceImgRepository placeImgRepository;
    private final HashtagAtPlaceRepository hashtagAtPlaceRepository;
    private final PlaceBookmarkRepository placeBookmarkRepository;
    private final PlaceFavoriteRepository placeFavoriteRepository;
    private final PlaceOpenRepository placeOpenRepository;
    private final PlaceReviewRepository placeReviewRepository;
    private final PlaceReviewFavoriteRepository placeReviewFavoriteRepository;

    // And create the API client
    //private final ElasticsearchClient elasticsearchClient;

    // URL and API key
    @Value("${elasticsearch.server.url}")
    private String serverUrl;

    @Value("${elasticsearch.api.key}")
    private String apiKey;

    @Value("${elasticsearch.ssl.fingerprint}")
    private String sslFingerPrint;

    @Value("${elasticsearch.username}")
    private String elasticUsername;

    @Value("${elasticsearch.password}")
    private String elasticPassword;

    public PlaceApiController.PlaceReadTest findElasticSearch(String placeId) throws Exception {

        SSLContext sslContext = TransportUtils
                .sslContextFromCaFingerprint(sslFingerPrint);

        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials(elasticUsername, elasticPassword)
        );

        // Create the low-level client
        RestClient restClient = RestClient
                .builder(HttpHost.create(serverUrl))
                .setHttpClientConfigCallback(hc -> hc
                        .setSSLContext(sslContext)
                        .setDefaultCredentialsProvider(credentialsProvider)
                        .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE))
                .build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        // And create the API client
        ElasticsearchClient elasticsearchClient = new ElasticsearchClient(transport);

        GetResponse<ObjectNode> response = elasticsearchClient.get(g -> g
                        .index("place")
                        .id(placeId),
                ObjectNode.class
        );

        if(!response.found()) {
            throw new NotFoundPlaceAtElasticSearchException(ErrorCode.NOT_FOUND_PLACE_AT_ELASTIC_SEARCH);
        }

        ObjectNode json = response.source();
        PlaceApiController.PlaceReadTest placeReadTest = new PlaceApiController.PlaceReadTest(
                json.get("_id").asText(),
                json.get("_score").asLong()
        );

        return placeReadTest;
    }

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
     * 유저가 공간을 북마크했는지 여부를 확인하기.
     */
    /*
    public boolean checkBookmark(String userid, Long placeId) {

        Optional<PlaceBookmark> placeBookmark = placeBookmarkRepository.findByIds(userid, placeId);
        if(!placeBookmark.isPresent()) return false;
        else return true;
    }

     */

    /**
     * 공간의 좋아요 개수 조회하기.
     */
    public int getFavoriteCount(Long id) {

        return placeFavoriteRepository.countFavorite(id);
    }

    /**
     * 해당 공간에 대한 유저의 좋아요 여부 확인하기.
     */
    public boolean checkFavorite(String userId, Long placeId) {

        Optional<PlaceFavorite> placeFavorite = placeFavoriteRepository.findByIds(userId, placeId);
        if(!placeFavorite.isPresent()) return false;
        else return true;
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

    /**
     * 엘라스틱 get 테스트
     */
    public ResponseEntity<Object> getPlaceData(String url) {

        HashMap<String, Object> result = new HashMap<String, Object>();
        ResponseEntity<Object> resultMap = new ResponseEntity<>(null, null, 200);

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
            resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Object.class);

            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            System.out.println("error");
            System.out.println(e.toString());

            return resultMap;
        }
        catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body"  , "excpetion오류");
            System.out.println(e.toString());

            return resultMap;

        }

        return resultMap;
    }

}
