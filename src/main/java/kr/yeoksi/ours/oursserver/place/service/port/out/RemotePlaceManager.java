package kr.yeoksi.ours.oursserver.place.service.port.out;

import kr.yeoksi.ours.oursserver.place.domain.Place;

import java.util.Optional;

public interface RemotePlaceManager {

    Optional<Place> findById(String id);

}
