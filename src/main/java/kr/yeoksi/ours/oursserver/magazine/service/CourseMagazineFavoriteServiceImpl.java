package kr.yeoksi.ours.oursserver.magazine.service;

import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineFavoriteService;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.CourseMagazineFavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CourseMagazineFavoriteServiceImpl implements CourseMagazineFavoriteService {

    private final CourseMagazineFavoriteRepository courseMagazineFavoriteRepository;


    @Override
    public void addFavorite(Long userId, Long courseMagazineId) {

    }

    @Override
    public void deleteFavorite(Long userId, Long courseMagazineId) {

    }

    @Override
    public boolean isFavorite(Long userId, Long courseMagazineId) {
        return false;
    }
}
