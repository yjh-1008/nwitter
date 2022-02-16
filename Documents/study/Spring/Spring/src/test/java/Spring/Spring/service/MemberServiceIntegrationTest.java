package Spring.Spring.service;

import Spring.Spring.domain.Member;
import Spring.Spring.repository.MemberRepository;
import Spring.Spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceIntegrationTest {
    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId= memberService.join(member);
        //then
        Member result=memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(result.getName());
    }
    @Test
    public void 중복회원예외(){
        //given
        Member member1= new Member();
        member1.setName("spring");


        Member member2= new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);

        IllegalStateException e=org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class,
                ()->memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try{
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        }catch(IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //then
    }
}