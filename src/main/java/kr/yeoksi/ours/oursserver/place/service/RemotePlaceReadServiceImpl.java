package kr.yeoksi.ours.oursserver.place.service;

import kr.yeoksi.ours.oursserver.place.domain.Place;
import kr.yeoksi.ours.oursserver.place.service.port.in.RemotePlaceReadService;
import org.springframework.stereotype.Service;


@Service
public class RemotePlaceReadServiceImpl implements RemotePlaceReadService {
    @Override
    public Place findById(String id) {
        return null;
    }
}
