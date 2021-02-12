package pure_Java_core.pure_core;

import pure_Java_core.pure_core.Order.OrderService;
import pure_Java_core.pure_core.Order.OrderServiceImpl;
import pure_Java_core.pure_core.dicount.DiscountPolicy;
import pure_Java_core.pure_core.dicount.FixDiscountPolicy;
import pure_Java_core.pure_core.dicount.RateDiscountPolicy;
import pure_Java_core.pure_core.member.MemberMemoryRepository;
import pure_Java_core.pure_core.member.MemberRepository;
import pure_Java_core.pure_core.member.MemberService;
import pure_Java_core.pure_core.member.MemberServiceImpl;

public class AppConfig {

    //이렇게 리펙토링하면 각 역할을 쉽게 볼 수 있음.
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemberMemoryRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
        //return new FixDiscountPolicy();
    }

}
