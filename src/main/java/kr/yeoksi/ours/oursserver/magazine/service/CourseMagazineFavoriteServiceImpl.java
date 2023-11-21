package kr.yeoksi.ours.oursserver.magazine.service;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazineFavorite;
import kr.yeoksi.ours.oursserver.magazine.exception.DuplicatedFavoriteException;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineFavoriteService;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.CourseMagazineFavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseMagazineFavoriteServiceImpl implements CourseMagazineFavoriteService {

    private final CourseMagazineFavoriteRepository courseMagazineFavoriteRepository;


    @Override
    @Transactional
    public void addFavorite(String userId, Long courseMagazineId) {
        if (isFavorite(userId, courseMagazineId)) {
            throw new DuplicatedFavoriteException("이미 좋아하는 매거진입니다.");
        }

        courseMagazineFavoriteRepository.save(
                CourseMagazineFavorite.builder()
                        .userId(userId)
                        .courseMagazineId(courseMagazineId)
                        .build()
        );
    }

    @Override
    @Transactional
    public void deleteFavorite(String userId, Long courseMagazineId) {
        CourseMagazineFavorite courseMagazineFavorite = courseMagazineFavoriteRepository.findByUserIdAndCourseMagazineId(userId, courseMagazineId)
                .orElseThrow(() -> new DuplicatedFavoriteException("좋아요를 표시하지 않은 매거진입니다."));

        courseMagazineFavoriteRepository.deleteById(courseMagazineFavorite.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isFavorite(String userId, Long courseMagazineId) {
        return courseMagazineFavoriteRepository.existsByUserIdAndCourseMagazineId(userId, courseMagazineId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseMagazine> getFavoriteMagazines(String userId, int page, int size) {
        List<CourseMagazineFavorite> favorites = courseMagazineFavoriteRepository.findByUserIdWithPage(userId, page, size);
        return favorites.stream()
                .map(CourseMagazineFavorite::getCourseMagazine)
                .toList();
    }
}
