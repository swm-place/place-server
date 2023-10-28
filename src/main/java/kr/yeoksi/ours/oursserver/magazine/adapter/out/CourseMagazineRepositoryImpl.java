package kr.yeoksi.ours.oursserver.magazine.adapter.out;


import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.entity.CourseMagazineJpaEntity;
import kr.yeoksi.ours.oursserver.magazine.adapter.out.jpa.CourseMagazineJpaRepository;
import kr.yeoksi.ours.oursserver.magazine.domain.CourseMagazine;
import kr.yeoksi.ours.oursserver.magazine.service.port.out.CourseMagazineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CourseMagazineRepositoryImpl implements CourseMagazineRepository {

    private final CourseMagazineJpaRepository courseMagazineJpaRepository;


    @Override
    public CourseMagazine save(CourseMagazine courseMagazine) {
        CourseMagazineJpaEntity jpaEntity = CourseMagazineJpaEntity.from(courseMagazine);
        return courseMagazineJpaRepository.save(jpaEntity).toCourseMagazine();
    }

    @Override
    public Optional<CourseMagazine> findById(Long id) {
        return courseMagazineJpaRepository.findById(id).map(CourseMagazineJpaEntity::toCourseMagazine);
    }

    @Override
    public List<CourseMagazine> findPageOrderByCreatedAtDesc(int count, int page) {
        PageRequest pageRequest = PageRequest.of(page, count, Sort.by(Sort.Direction.DESC, "createdAt"));

        return courseMagazineJpaRepository.findAll(pageRequest)
                .toList().stream()
                .map(CourseMagazineJpaEntity::toCourseMagazine)
                .toList();
    }

    @Override
    public void delete(Long id) {
        courseMagazineJpaRepository.deleteById(id);
    }
}
