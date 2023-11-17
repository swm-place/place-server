package kr.yeoksi.ours.oursserver.others.jpa.repository;

import kr.yeoksi.ours.oursserver.others.domain.PlaceInBookmark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceInBookmarkJpaRepository extends JpaRepository<PlaceInBookmark, Long> {

    List<PlaceInBookmark> findAllByPlaceBookmark_Id(Long placeBookmarkId, Pageable pageable);
}
