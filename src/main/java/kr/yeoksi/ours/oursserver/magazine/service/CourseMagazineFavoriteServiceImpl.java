package kr.yeoksi.ours.oursserver.magazine.service;

import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineFavoriteService;

public class CourseMagazineFavoriteServiceImpl implements CourseMagazineFavoriteService {
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
