package pure_Java_core.pure_core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pure_Java_core.pure_core.Order.Order;
import pure_Java_core.pure_core.Order.OrderService;
import pure_Java_core.pure_core.Order.OrderServiceImpl;
import pure_Java_core.pure_core.member.*;

public class OrderApp {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);


        //Appconfig에서 서비스들을 꺼낼 수 있다.
 //       AppConfig appConfig = new AppConfig();
 //       MemberService memberService = appConfig.memberService();
        //OrderService orderService = new OrderServiceImpl(null,null);
        //MemberService memberService = new MemberServiceImpl(null);

        long memberId = 1L;
        Member member = new Member(memberId, "MemberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "치킨", 20000);
        System.out.println("order.toString() = " + order.toString());
        System.out.println("order.calculatePrice() = " + order.calculatePrice());


    }
}
