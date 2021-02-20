package pure_Java_core.pure_core.autoWired;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pure_Java_core.pure_core.AutoAppConfig;
import pure_Java_core.pure_core.dicount.DiscountPolicy;
import pure_Java_core.pure_core.member.Grade;
import pure_Java_core.pure_core.member.Member;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {
    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "MemberA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int price = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(price).isEqualTo(2000);

    }


    static class DiscountService{
        private final Map<String, DiscountPolicy> policymap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policymap, List<DiscountPolicy> policies) {
            this.policymap = policymap;
            this.policies = policies;
            System.out.println("policymap = " + policymap);
            System.out.println("policies = " + policies);
        }


        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policymap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
