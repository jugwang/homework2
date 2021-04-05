package sns.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sns.demo.domain.Member;
import sns.demo.service.MemberService;

@Controller
public class ViewController {

    //필드주입 , 생성자주입
    //Autowired
    //private MemberService memberservice;

    private final MemberService memberService;

    public ViewController(MemberService memberService) {
        this.memberService = memberService;}

    @GetMapping("/")
    public String Home() {
        return "/home";
    }

    @RequestMapping(value = "/login")
    public ModelAndView LoginPage() {
        ModelAndView mav = new ModelAndView("members/memberLogin");
        return mav;
    }

    @RequestMapping(value = "/signup")
    public String SignUpPage(Member member) {
//        ModelAndView mav = new ModelAndView("members/newmember");
        return "members/newmember";
    }

    @RequestMapping(value = "/loginCheck")
    public ModelAndView LoginCheck() {
        ModelAndView mav = new ModelAndView("members/loginCheck");
        return mav;
    }

    @RequestMapping(value = "loginFail")
    public ModelAndView LoginFail() {
        ModelAndView mav = new ModelAndView("members/loginFail");
        return mav;
    }
}


