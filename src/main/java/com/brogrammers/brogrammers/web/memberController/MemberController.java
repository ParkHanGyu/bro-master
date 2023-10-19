package com.brogrammers.brogrammers.web.memberController;

import com.brogrammers.brogrammers.domain.member.Member;
import com.brogrammers.brogrammers.domain.member.Address;
import com.brogrammers.brogrammers.domain.service.LoginService;
import com.brogrammers.brogrammers.web.session.SessionConst;
import com.brogrammers.brogrammers.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller // 어노테이션은 이 클래스가 웹 요청을 처리하는 컨트롤러임을 나타냅니다.
@RequiredArgsConstructor // 이 어노테이션은 필요한 종속성을 자동으로 주입해줍니다.
@Slf4j // 로깅을 위한 어노테이션입니다.
public class MemberController {
    // 회원과 관련된 비즈니스 로직을 처리하기 위한 서비스
    private final MemberService memberService;
    // 로그인 관련 비즈니스 로직을 처리하기 위한 서비스
    private final LoginService loginService;

    // 회원가입 폼을 보여주는 페이지로 이동하는 메서드에요.
    @GetMapping("/member/joinMember")
    public String joinMemberForm(Model model){
        // 회원 정보를 입력할 수 있는 폼을 보여주기 위해 모델에 데이터를 추가해요.
        model.addAttribute("form", new MemberForm());
        // 회원가입 폼 페이지로 이동합니다.
        return "member/joinMemberForm";
    }

    // 회원가입 정보를 제출할 때 사용되는 메서드에요.
    @PostMapping("/member/joinMember")
    public String joinMember(@Valid @ModelAttribute("form") MemberForm form,BindingResult bindingResult){
        // 만약 입력한 정보에 문제가 있다면 회원가입 폼으로 다시 보내요.
        if(bindingResult.hasErrors()){
            return "member/joinMemberForm";
        }

        // 회원 정보를 생성하고 저장해요.
        Member member = new Member();
        member = member.saveMember(form.getEmail(),form.getPwd(),form.getName());

        // 주소 정보를 입력하고 저장해요.
        if(form.getPostal_code()!=null &&form.getMiddle_address()!=null&&form.getDetailed_address()!=null){
            Address address = new Address(form.getPostal_code(),form.getMiddle_address(),form.getDetailed_address()); // 주소 입력 메서드
            member.saveAddress(address);
        }

        // 회원을 데이터베이스에 등록하고 회원가입이 완료되었음을 출력해요.
        long id = memberService.join(member);
        System.out.println("회원가입 완료 :" + id);

        // 홈페이지로 이동합니다.
        return "redirect:/";
    }

    // 고객센터 페이지로 이동하는 메서드에요.
    @PostMapping("/customer/customerService") // 고객센터
    public String customerService(){

        // 고객센터 페이지로 이동합니다.
        return "customer/customerService";
    }


    // 로그인 페이지로 이동하는 메서드에요.
    @GetMapping("/member/login")
    public String login(@ModelAttribute("form") LoginForm form,HttpServletRequest request){

        HttpSession session = request.getSession(false);

        if(session != null){
            // 이미 로그인된 사용자가 있다면 홈페이지로 이동해요.
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            if(loginMember!=null){
                return "redirect:/";
            }
        }
        // 로그인 폼 페이지로 이동합니다.
        return "member/loginForm";
    }

    // 로그인 정보를 제출할 때 사용되는 메서드에요.
    @PostMapping("/member/login")
    public String memberLogin(@Valid @ModelAttribute("form") LoginForm form, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "member/loginForm";
        }

        // 입력한 로그인 정보를 검사하고, 올바르지 않다면 다시 로그인 폼으로 보내요.
        Member loginMember = loginService.login(form.getMemberEmail(),form.getPassword());
        if(loginMember==null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호 오류");
            return "member/loginForm";
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        // 홈페이지로 이동합니다.
        return "redirect:/";
    }

    // 로그아웃을 처리하는 메서드에요.
    @PostMapping("/logout")
    public String logout3(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            // 세션을 무효화시켜 로그아웃해요.
            session.invalidate();
        }
        // 홈페이지로 이동합니다.
        return "redirect:/";
    }

    // 로그아웃을 처리하는 메서드에요.
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            // 세션을 무효화시켜 로그아웃해요.
            session.invalidate();
        }
        // 홈페이지로 이동합니다.
        return "redirect:/";
    }
}

