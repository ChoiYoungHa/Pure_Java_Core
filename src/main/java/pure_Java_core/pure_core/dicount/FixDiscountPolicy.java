package pure_Java_core.pure_core.dicount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pure_Java_core.pure_core.member.Grade;
import pure_Java_core.pure_core.member.Member;
@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; //1000원 할인
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }else {
            return 0;
        }
    }
}
