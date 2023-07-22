package kr.yeoksi.ours.oursserver.service;

import kr.yeoksi.ours.oursserver.domain.TermsOfService;
import kr.yeoksi.ours.oursserver.domain.User;
import kr.yeoksi.ours.oursserver.exception.DuplicatedUserException;
import kr.yeoksi.ours.oursserver.exception.ErrorCode;
import kr.yeoksi.ours.oursserver.repository.TermsOfServiceRepository;
import kr.yeoksi.ours.oursserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TermsOfServiceRepository termsOfServiceRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public String signUp(User user) {

        // 회원 중복 체크가 필요한가? -> 파이어베이스가 해주나?
        Optional<User> duplicatedUser = userRepository.checkDuplicatedUser(user.getId());
        if(duplicatedUser.isPresent()) {
            // 이미 존재하는 유저인 경우
            throw new DuplicatedUserException(ErrorCode.DUPLICATED_USER);
        }

        // 회원 저장
        userRepository.save(user);

        return user.getId();
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
