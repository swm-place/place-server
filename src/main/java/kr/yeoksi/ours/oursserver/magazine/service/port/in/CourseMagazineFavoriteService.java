package kr.yeoksi.ours.oursserver.magazine.service.port.in;

public interface CourseMagazineFavoriteService {

    void addFavorite(Long userId, Long courseMagazineId);

    void deleteFavorite(Long userId, Long courseMagazineId);

    boolean isFavorite(Long userId, Long courseMagazineId);

}
