package com.brogrammers.brogrammers.web.controller;

import com.brogrammers.brogrammers.web.memberController.MemberController;
import com.brogrammers.brogrammers.sevice.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// OAuthController 클래스는 OAuth(Open Authorization) 관련 작업을 처리하는 컨트롤러에요.
@RestController // 이 클래스는 RESTful API를 위한 컨트롤러로 사용되며, JSON 등의 데이터를 반환할 수 있습니다.
@Controller
@RequestMapping(value = "/auth/kakao/*", produces = "text/plain;charset=UTF-8")
public class OAuthController {

    @Autowired
    OAuthService oAuthService; // OAuthService 타입의 oAuthService 인스턴스를 주입받습니다.
    @Autowired
    MemberController memberController; // MemberController 타입의 memberController 인스턴스를 주입받습니다.

    /* 카카오 콜백 */
    @ResponseBody // 이 메서드는 응담 데이터를 직접 반환합니다.
    @GetMapping("/callback") // "/auth/kakao/callback" 경로로 들어온 GET 요청을 처리하는 메서드입니다.
    public void kakaoCallback(@RequestParam(value = "code", required = false) String code) throws Exception {
        System.out.println("########" + code); // code 값 출력 (카카오 콜백에서 전달된 값)
        String code2 = oAuthService.getKakaoAccessToken(code); // 카카오 액세스 토큰을 얻는 메서드 호출
        System.out.println("====================");
        String email = oAuthService.createKakaoUser(code2); // 카카오 사용자를 생성하는 메서드 호출
    }
}

