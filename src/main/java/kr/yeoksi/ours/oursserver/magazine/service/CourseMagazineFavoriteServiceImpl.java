package kr.yeoksi.ours.oursserver.magazine.service;

import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazineFavorite;
import kr.yeoksi.ours.oursserver.magazine.exception.DuplicatedFavoriteException;
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
        if (courseMagazineFavoriteRepository.existsByUserIdAndCourseMagazineId(userId, courseMagazineId)) {
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
    public void deleteFavorite(Long userId, Long courseMagazineId) {
        CourseMagazineFavorite courseMagazineFavorite = courseMagazineFavoriteRepository.findByUserIdAndCourseMagazineId(userId, courseMagazineId)
                .orElseThrow(() -> new DuplicatedFavoriteException("좋아요를 표시하지 않은 매거진입니다."));

        courseMagazineFavoriteRepository.delete(courseMagazineFavorite);
    }

    @Override
    public boolean isFavorite(Long userId, Long courseMagazineId) {
        return courseMagazineFavoriteRepository.existsByUserIdAndCourseMagazineId(userId, courseMagazineId);
    }
}
