package hello.login.domain.member;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {
    private final Map<Long, Member> store = new ConcurrentHashMap<>();
    private Long id = 0L;

    public void save(Member member) {

        store.put(id, Member.builder()
                .id(++id)
                .name(member.getName())
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .build());
    }

    public Optional<Member> findById(Long id) {
        return Optional.of(store.get(id));
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }



}
