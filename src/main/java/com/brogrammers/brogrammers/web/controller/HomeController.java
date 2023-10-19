package com.brogrammers.brogrammers.web.controller;

import com.brogrammers.brogrammers.domain.member.Member;
import com.brogrammers.brogrammers.domain.repository.MemberRepository;
import com.brogrammers.brogrammers.web.session.SessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired // MemberRepository의 인스턴스를 자동으로 주입하도록 지정
    private MemberRepository memberRepository;

    // HTTP GET 요청을 처리하는 메소드
    @GetMapping(value = {"/home","/",""})
    // HTTP GET 요청을 처리 메소드의 매개변수로 HttpServletRequest와 Model을 받는다
    public String home(HttpServletRequest request, Model model){

        // 새로운 세션을 가져오거나 기존 세션을 가져오는 역할
        // 현재 요청에 대한 세션 객체를 가져온다.
        // 세션이 이미 존재하면 해당 세션을 반환하고, 세션이 존재하지 않으면 null을 반환
        HttpSession session = request.getSession(false);
        if(session==null){
            return "index2";
        }

        // 세션에서 사용자 정보를 가져오는 역할
        // web/session/SessionConst.java
        // 세션에서 "LOGIN_MEMBER"라는 속성을 가져와서
        // 이를 Member 객체로 형변환하여 member 변수에 저장
        Member member = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(member==null){
            return "index2";
        }


        // 로그인 세션이 있을때
        System.out.println("loginHome");

        // 데이터를 View(웹 페이지)로 전달하는 역할
        model.addAttribute("member",member);
        // loginHome.html로 전달
        return "loginHome";
    }

    // findCookie 메소드를 정의하며, 클라이언트로부터 전달된 HTTP 요청에서 특정 쿠키를 찾는 역할
    public Cookie findCookie(HttpServletRequest request,String cookieName){
        //현재 요청에서 모든 쿠키를 가져와서 cookies 배열에 저장
        Cookie[] cookies = request.getCookies();
        // 요청에 쿠키가 없을 때, null을 반환
        if(cookies==null){
            return null;
        }

        // 요청에 쿠키가 있을 때
        System.out.println("메서드 실행");

        // cookies 배열을 스트림으로 변환 -> filter 함수를 사용하여 쿠키의 이름이
        // cookieName과 일치하는 쿠키를 찾기 ->  .findAny()를 호출하여 어떤 쿠키라도
        // 찾아서 반환하며, 쿠키를 찾지 못한 경우 null을 반환
        return Arrays.stream(cookies)
                .filter(cookie->cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }



}
