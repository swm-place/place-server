package kr.yeoksi.ours.oursserver.others.jpa.repository;

import kr.yeoksi.ours.oursserver.others.domain.PlaceFavorite;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceFavoriteJpaRepository extends JpaRepository<PlaceFavorite, Long> {

    List<PlaceFavorite> findAllByUser_Id(String userId, Pageable pageable);
}
