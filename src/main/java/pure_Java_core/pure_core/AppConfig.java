package pure_Java_core.pure_core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pure_Java_core.pure_core.Order.OrderService;
import pure_Java_core.pure_core.Order.OrderServiceImpl;
import pure_Java_core.pure_core.dicount.DiscountPolicy;
import pure_Java_core.pure_core.dicount.FixDiscountPolicy;
import pure_Java_core.pure_core.dicount.RateDiscountPolicy;
import pure_Java_core.pure_core.member.MemberMemoryRepository;
import pure_Java_core.pure_core.member.MemberRepository;
import pure_Java_core.pure_core.member.MemberService;
import pure_Java_core.pure_core.member.MemberServiceImpl;

@Configuration
public class AppConfig {

    //이렇게 리펙토링하면 각 역할을 쉽게 볼 수 있음.

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemberMemoryRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
        //return new FixDiscountPolicy();
    }

}
