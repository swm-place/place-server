package kr.yeoksi.ours.oursserver.place.service.port.out;

import kr.yeoksi.ours.oursserver.place.domain.Place;

public interface RemotePlaceManager {

    Place findById(String id);

}
