package pure_Java_core.pure_core.dicount;

import pure_Java_core.pure_core.member.Grade;
import pure_Java_core.pure_core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int rateDiscount = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * rateDiscount / 100;
        }else{
            return 0;
        }
    }
}