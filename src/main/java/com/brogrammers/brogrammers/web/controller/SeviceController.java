package com.brogrammers.brogrammers.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// SeviceController 클래스는 웹 서비스 관련 페이지의 경로를 처리하는 컨트롤러에요.
@Controller
public class SeviceController {

    // "/service/customerService" 경로로 GET 요청이 들어오면 이 메서드를 실행합니다.
    // 이 메서드는 고객센터 페이지를 보여주는 역할을 합니다.
    @GetMapping("/service/customerService") /*고객센터*/
    public String customerService() {
        return "service/customerService"; // "service/customerService" 뷰를 반환합니다.
    }

    // "/service/style" 경로로 GET 요청이 들어오면 이 메서드를 실행합니다.
    // 이 메서드는 SNS 관련 페이지를 보여주는 역할을 합니다.
    @GetMapping("/service/style") /* sns */
    public String style() {
        return "service/style"; // "service/style" 뷰를 반환합니다.
    }
}
