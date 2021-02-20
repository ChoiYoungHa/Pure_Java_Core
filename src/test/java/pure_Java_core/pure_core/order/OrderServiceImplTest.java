package pure_Java_core.pure_core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pure_Java_core.pure_core.Order.Order;
import pure_Java_core.pure_core.Order.OrderServiceImpl;
import pure_Java_core.pure_core.dicount.FixDiscountPolicy;
import pure_Java_core.pure_core.member.Grade;
import pure_Java_core.pure_core.member.Member;
import pure_Java_core.pure_core.member.MemberMemoryRepository;
import pure_Java_core.pure_core.member.MemberRepository;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceImplTest {
    @Test
    void createOrder() {
        //생성자 주입을 사용하면 임의의 테스트할 때도 무언가를 꼭 넣어야한다고 컴파일 오류가 뜬다.
        //세상에서 가장 좋은 오류가 컴파일 오류 뭐가 잘못됬는지 알려주기 때문임.
        MemberRepository memberRepository = new MemberMemoryRepository();
        memberRepository.save(new Member(1L, "MemberA", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(new MemberMemoryRepository(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "Item1", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
