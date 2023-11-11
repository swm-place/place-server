package kr.yeoksi.ours.oursserver.place.adapter.out;

import kr.yeoksi.ours.oursserver.place.domain.Place;
import kr.yeoksi.ours.oursserver.place.service.port.out.RemotePlaceManager;
import org.springframework.stereotype.Component;


@Component
public class RemotePlaceManagerImpl implements RemotePlaceManager {
    @Override
    public Place findById(String id) {
        return null;
    }
}
