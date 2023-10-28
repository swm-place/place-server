package kr.yeoksi.ours.oursserver.magazine.adapter.out;


import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.CourseMagazineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CourseMagazineRepositoryImpl implements CourseMagazineRepository {

    private final CourseMagazineJpaRepository courseMagazineJpaRepository;

    
    @Override
    public CourseMagazine save(CourseMagazine courseMagazine) {
        return null;
    }

    @Override
    public Optional<CourseMagazine> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<CourseMagazine> findLatestCourseMagazines(int count) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
