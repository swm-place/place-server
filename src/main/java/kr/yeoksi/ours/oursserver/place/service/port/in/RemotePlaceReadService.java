package kr.yeoksi.ours.oursserver.place.service.port.in;

import kr.yeoksi.ours.oursserver.place.domain.Place;

import java.util.Optional;

public interface RemotePlaceReadService {

    Optional<Place> findById(String id);

}
