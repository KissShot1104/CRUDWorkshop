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
        member.setId(++id);
        store.put(id, member);
    }

    public Optional<Member> findOne(Long id) {
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
