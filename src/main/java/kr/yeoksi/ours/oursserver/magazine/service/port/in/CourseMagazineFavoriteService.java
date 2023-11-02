package kr.yeoksi.ours.oursserver.magazine.service.port.in;

public interface CourseMagazineFavoriteService {

    void addFavorite(String userId, Long courseMagazineId);

    void deleteFavorite(String userId, Long courseMagazineId);

    boolean isFavorite(String userId, Long courseMagazineId);

}
