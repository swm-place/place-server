package kr.yeoksi.ours.oursserver.place.service.port.in;

import kr.yeoksi.ours.oursserver.place.domain.Place;

public interface RemotePlaceReadService {

    Place findById(String id);

}
