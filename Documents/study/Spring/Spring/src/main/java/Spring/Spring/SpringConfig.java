package Spring.Spring;

import Spring.Spring.app.TimeTraceAop;
import Spring.Spring.repository.*;
import Spring.Spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService(){

        return new MemberService(memberRepository);
    }

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }

    //@Bean
   // public MemberRepository memberRepository(){
     //   return new JpaMemberRepository(em);
    //}
}
