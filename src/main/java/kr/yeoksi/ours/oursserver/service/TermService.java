package kr.yeoksi.ours.oursserver.service;

import kr.yeoksi.ours.oursserver.domain.TermsOfService;
import kr.yeoksi.ours.oursserver.repository.TermsOfServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TermService {

    private final TermsOfServiceRepository termsOfServiceRepository;

    /**
     * 이용약관 저장하기
     */
    public void saveTerm(TermsOfService term) {
        termsOfServiceRepository.save(term);
    }

    /**
     * 사용자가 동의한 이용 약관들 가져오기
     */
    public List<TermsOfService> getAgreedTerms(List<Long> agreedTermsIndex) {

        List<TermsOfService> agreedTerms = new ArrayList<>();
        if(!CollectionUtils.isEmpty(agreedTermsIndex)) {
            for(Long id : agreedTermsIndex) {
                agreedTerms.add(termsOfServiceRepository.findById(id));
            }
        }
        return  agreedTerms;
    }
}
