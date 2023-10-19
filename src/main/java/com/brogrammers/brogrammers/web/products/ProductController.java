package com.brogrammers.brogrammers.web.products;

import com.brogrammers.brogrammers.domain.member.Accessrigths;
import com.brogrammers.brogrammers.domain.member.Member;
import com.brogrammers.brogrammers.domain.product.Products;
import com.brogrammers.brogrammers.domain.service.MemberService;
import com.brogrammers.brogrammers.domain.service.ProductService;
import com.brogrammers.brogrammers.web.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    MemberService memberService;

    @GetMapping("/products/add")
    public String addForm(@ModelAttribute("form")AddProductForm form ,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null){
            return "redirect:/";
        }
        Member member = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(member==null ){
            // 없는 회원이거나
            return "redirect:/";
        }
        if(member.getAccessrigths()== Accessrigths.NORMAL){
            //회원 권한이 NOMAL일 경우 index페이지로 이동
            return "redirect:/";
        }
        return "products/add";
    }
    @PostMapping("/products/add")
    public String add(@Valid @ModelAttribute("form")AddProductForm form , HttpServletRequest request,Model model){
        Member member = getMember(request); // 조회된 회원 아이디
        form.setMember(member); // 상품 등록할 때 회원 아이디 입력하기
        Products products = new Products();
        products.saveProduct(form.getProductName(), form.getProductBrand(),form.getProductColor(),form.getPrice(),form.getSize(), form.getStockQuantity(),form.getMember());
        productService.save(products);
        model.addAttribute("mgs","상품 등록이 완료되었습니다.");
        model.addAttribute("product",products);
        return "products/detail"; // 상품 상세보기 페이지로 넘어갈 예정
    }
    @GetMapping("/products/list")  // 로그인 정보에 맞는 상품 리스트 불러오기 ?
    public String productList(Model model,HttpServletRequest request){
        List<Products> products = productService.findAll();
        model.addAttribute("products",products);
        return "products/list";
    }
    @GetMapping("/products/myList")  // 로그인 정보에 맞는 상품 리스트 불러오기 ?
    public String allMyProducts(Model model,HttpServletRequest request){
        Member member = getMember(request); // 세션에서 멤버정보 불러오기
        member = memberService.findById(member.getId());
        System.out.println("memberid" + member.getId() + member.getName());
//        List<Products> products =  productService.findAllByMemberId(member.getId()); // 로그인된 id 값으로 상품 리스트 불러오기

        List<Products> products = member.getProdutcs();
        System.out.println("=============" + products);
        model.addAttribute("products",products);
        return "products/myList";
    }

    @GetMapping("/products/{productId}/detail") // 디테일 페이지 컨트롤러
    public String detail(@PathVariable("productId")Long productId,Model model){
        Products product = productService.findById(productId); // 세션에서 멤버정보 불러오기
        model.addAttribute("product", product);
        return "products/detail";
    }

    private Member getMember(HttpServletRequest request) { // 세션에서 회원 아이디 조회
        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        return member;
    }
}
