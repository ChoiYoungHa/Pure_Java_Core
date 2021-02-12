package pure_Java_core.pure_core.Order;

import pure_Java_core.pure_core.dicount.DiscountPolicy;
import pure_Java_core.pure_core.dicount.FixDiscountPolicy;
import pure_Java_core.pure_core.member.Member;
import pure_Java_core.pure_core.member.MemberMemoryRepository;
import pure_Java_core.pure_core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{
    public final MemberRepository memberRepository;
    public final DiscountPolicy discountPolicy; // DIP 지킨거 추상화에만 의존

    //constructor injection (생성자 주입)
    //의존 관계 주입 Dependency Injection DI주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discount = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discount);
    }
}
