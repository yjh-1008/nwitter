package Spring.Spring.controller;

import Spring.Spring.domain.Member;
import Spring.Spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {


    private final MemberService memberService;
    
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }
    @GetMapping("members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    @PostMapping("members/new")//폼단위로 데이터가 들어올 때 PostMapping 사
    public String create(MemberForm form){
        Member member =new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members= memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberlist";
    }
}
