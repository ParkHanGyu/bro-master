package com.brogrammers.brogrammers.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// MenuController 클래스는 메뉴 관련 페이지를 처리하는 컨트롤러에요.
@Controller
public class MenuController {

    // totalMenu() 메서드는 "/menu/total" 경로로 들어오는 GET 요청을 처리하는 메서드에요.
    @GetMapping("/menu/total")
    public String totalMenu(){
        // "menu/total" 뷰(페이지)로 이동합니다.
        return "menu/total";
    }
}
