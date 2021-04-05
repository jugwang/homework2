package sns.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sns.demo.domain.Member;
import sns.demo.service.MemberService;

@Controller
public class ViewController {

    private MemberService memberService;

    @Autowired
    public ViewController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String Home() {
        return "/members/home";
    }

    @GetMapping(value = "newMember")
    public String createMember() {
        return "members/newmember";
    }

    @PostMapping("newMember")
    public String create() {
        Member newMember = new Member();
        memberService.createMember(newMember.getId(), newMember.getName(), newMember.getPassword());
        memberService.removeMember(newMember.getId());
        return "members/newmember";
    }

    @GetMapping("memberLogin")
    public String MemberLogin() {
        return "/members/memberLogin";
    }

    @PostMapping("memberLogin")
    public String Login() {
        Member newMember = new Member();
        memberService.login(newMember.getId(), newMember.getPassword());
        memberService.update(newMember.getId(), newMember.getPassword(), newMember.getName());
        return "/members/memberLogin";
    }
}


