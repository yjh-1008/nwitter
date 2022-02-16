package Spring.Spring.repository;

import Spring.Spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository=new MemoryMemberRepository();

    @AfterEach
    //Test케이스가 한 번씩 끝날 때마다 실행.
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);


        //findById의 리턴값은 Optional이지만 get()함수를 통하여 Optional을에 들어가는 Member를 받는다.
        Member result = repository.findById(member.getId()).get();
        //result 와 member가 같다면 정상적으로 실행.
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result1 =repository.findByName("spring1").get();

        Assertions.assertThat(result1).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result=repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
