package kr.yeoksi.ours.oursserver.place.adapter.out;

import kr.yeoksi.ours.oursserver.place.adapter.out.object.RemotePlace;
import kr.yeoksi.ours.oursserver.place.domain.Place;
import kr.yeoksi.ours.oursserver.place.service.port.out.RemotePlaceManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Component
public class RemotePlaceManagerImpl implements RemotePlaceManager {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("{internal.server.recommender.url}")
    private String recommenderUrl;


    @Override
    public Optional<Place> findById(String id) {
        Optional<RemotePlace> remotePlace;

        try {
            remotePlace = Optional.ofNullable(restTemplate.getForObject(
                    String.format("%s/places/%s", this.recommenderUrl, id),
                    RemotePlace.class));
        } catch (HttpStatusCodeException e) {
            return Optional.empty();
        }

        return remotePlace.map(RemotePlace::toPlace);
    }

}
