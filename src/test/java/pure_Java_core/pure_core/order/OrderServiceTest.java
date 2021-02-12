package pure_Java_core.pure_core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pure_Java_core.pure_core.AppConfig;
import pure_Java_core.pure_core.Order.Order;
import pure_Java_core.pure_core.Order.OrderService;
import pure_Java_core.pure_core.Order.OrderServiceImpl;
import pure_Java_core.pure_core.member.*;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;
    Long memberId = 1L;

    @BeforeEach
    public void BeforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        //give
        Member member = new Member(memberId, "MemberB", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(memberId, "치킨", 11000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1100);
    }

}
