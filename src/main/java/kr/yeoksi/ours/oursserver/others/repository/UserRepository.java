package kr.yeoksi.ours.oursserver.others.repository;

import jakarta.persistence.EntityManager;
import kr.yeoksi.ours.oursserver.others.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    /**
     * 유저 저장하기.
     */
    public void save(User user) {
        em.persist(user);
    }

    /**
     * id로 유저 찾기
     */
    public Optional<User> findById(String id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    /**
     * id를 이용해 이미 존재하는 유저인지 확인하기
     */
    public Optional<User> checkDuplicatedUser(String id) {
        return em.createQuery(
                "SELECT u FROM User u " +
                        "WHERE u.id =: id", User.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findAny();
    }

    /**
     * email로 유저 찾기
     */
    public Optional<User> findByEmail(String email) {
        return em.createQuery(
                "SELECT u FROM User u " +
                        "WHERE u.email =: email ", User.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findAny();
    }

    /**
     * nickname으로 유저 찾기
     */
    public Optional<User> findByNickname(String nickname) {
        return em.createQuery(
                "SELECT u FROM User u " +
                        "WHERE u.nickname =: nickname ", User.class)
                .setParameter("nickname", nickname)
                .getResultList()
                .stream()
                .findAny();
    }
}
