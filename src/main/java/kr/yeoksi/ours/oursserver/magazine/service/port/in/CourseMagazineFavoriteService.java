package kr.yeoksi.ours.oursserver.magazine.service.port.in;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;

import java.util.List;

public interface CourseMagazineFavoriteService {

    void addFavorite(String userId, Long courseMagazineId);

    void deleteFavorite(String userId, Long courseMagazineId);

    boolean isFavorite(String userId, Long courseMagazineId);

    List<CourseMagazine> getFavoriteMagazines(String userId, int page, int size);

}
