package pure_Java_core.pure_core.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pure_Java_core.pure_core.annotation.MainDiscountPolicy;
import pure_Java_core.pure_core.dicount.DiscountPolicy;
import pure_Java_core.pure_core.member.Member;
import pure_Java_core.pure_core.member.MemberRepository;

@Component
// final이 붙는 객체들의 생성자를 자동으로 만들어주는 lombok 어노테이션
public class OrderServiceImpl implements OrderService{
    //final을 적어주면 생성자에서 의존관계 코드를 누락할 시 컴파일 오류를 발생시켜준다.
    //또한 this키워드를 빼먹어도 오류를 내준다. 생성자 주입을 사용하면 편한 이유중 하나다.
    public final MemberRepository memberRepository;
    public final DiscountPolicy discountPolicy; // DIP 지킨거 추상화에만 의존

    //discountPlicy는 현재 2개의 구현체 bean이 등록되어있음.
    //이를 명시적으로 dicountPolicy의 이름을 원하는 구현체의 이름으로 바꿔주거나,
    //@Qualifier 어노테이션을 사용해서 지정해줄 수 도 있다. 구현체에 @Qualifier("mainDiscount")
    //@Primary 어노테이션을 구현체에 붙혀주면 두개의 구현체중 프라이머리 어노테이션이 붙은것을 주입한다.
    //또한 @Qualifier를 사용해서 어노테이션을 만들어 사용하면 좀 더 깔끔하다.

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


//    //수정자 주입
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }

    //constructor injection (생성자 주입)
    //의존 관계 주입 Dependency Injection DI주입
    //생성자 주입을 lombok Annotation으로 대신함.
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discount = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discount);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
