package pure_Java_core.pure_core.dicount;

import pure_Java_core.pure_core.member.Member;

public interface DiscountPolicy {

    /**
     * @return
     * 할인 금액 리턴
     */
    int discount(Member member, int price);
}
