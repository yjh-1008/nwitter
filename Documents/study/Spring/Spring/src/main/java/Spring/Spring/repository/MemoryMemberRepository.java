package Spring.Spring.repository;

import Spring.Spring.domain.Member;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence =0L;



    @Override
    public Member save(Member member) {
       member.setId(++sequence);
       store.put(member.getId(),member);
       return member;
    }

    @Override
    public Optional<Member> findById(Long Id) {
        return Optional.ofNullable(store.get(Id)); //널값이어도 반환 가
    }

    @Override
    public Optional<Member> findByName(String name) {
        //스트림은 자바8부터 추가된 컬렉션의 저장 요소를 하나씩 참조해서 람다식으로 처리할 수 있도록 해주는 반복자입니다.
        return store.values().stream()
                .filter(member->member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
