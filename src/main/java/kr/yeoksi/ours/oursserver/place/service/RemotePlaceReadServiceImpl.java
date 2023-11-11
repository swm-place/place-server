package kr.yeoksi.ours.oursserver.place.service;

import kr.yeoksi.ours.oursserver.place.domain.Place;
import kr.yeoksi.ours.oursserver.place.service.port.in.RemotePlaceReadService;
import kr.yeoksi.ours.oursserver.place.service.port.out.RemotePlaceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RemotePlaceReadServiceImpl implements RemotePlaceReadService {

    private final RemotePlaceManager remotePlaceManager;


    @Override
    public Optional<Place> findById(String id) {
        // TODO: apply cache
        return remotePlaceManager.findById(id);
    }
}
