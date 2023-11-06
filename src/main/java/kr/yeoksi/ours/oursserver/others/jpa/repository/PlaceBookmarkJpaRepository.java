package kr.yeoksi.ours.oursserver.others.jpa.repository;

import kr.yeoksi.ours.oursserver.others.domain.PlaceBookmark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceBookmarkJpaRepository extends JpaRepository<PlaceBookmark, Long> {

    List<PlaceBookmark> findByUserId(String userId, Pageable pageable);
}