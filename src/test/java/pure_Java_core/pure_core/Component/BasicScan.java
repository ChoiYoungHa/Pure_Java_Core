package pure_Java_core.pure_core.Component;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pure_Java_core.pure_core.AutoAppConfig;
import pure_Java_core.pure_core.Order.Order;
import pure_Java_core.pure_core.Order.OrderService;
import pure_Java_core.pure_core.Order.OrderServiceImpl;
import pure_Java_core.pure_core.member.Grade;
import pure_Java_core.pure_core.member.Member;
import pure_Java_core.pure_core.member.MemberService;


public class BasicScan {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    @Test
    @DisplayName("컴포넌트 스캔")
    void scantest() {

        //orderService의 의존관계인 discountPolicy가 bean이 중복되어있어서 오류를 발생시킴
        MemberService memberService = ac.getBean(MemberService.class);
        OrderService bean = ac.getBean(OrderService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

//    @Test
//    @DisplayName("order 서비스 사용")
//    void orderServiceTest(){
//        MemberService memberService = ac.getBean(MemberService.class);
//        OrderService orderService = ac.getBean(OrderService.class);
//        Member member = new Member(1L, "Young", Grade.VIP);
//        memberService.join(member);
//        Order item2 = orderService.createOrder(1L, "Item2", 12000);
//        System.out.println("item2.toString() = " + item2.toString());
//
//    }
}
