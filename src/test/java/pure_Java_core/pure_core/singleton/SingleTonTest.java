package pure_Java_core.pure_core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pure_Java_core.pure_core.AppConfig;
import pure_Java_core.pure_core.member.MemberService;

import static org.assertj.core.api.Assertions.*;

public class SingleTonTest {
    @Test
    @DisplayName("스프링없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig ap = new AppConfig();

        //1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = ap.memberService();

        //2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = ap.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("순수자바로 싱글톤 패턴을 적용한 객체 사용")
        //객체를 생성할 때 마다 하나의 객체로 고정 자원낭비를 방지
        //다만 ConfigApp의 bean들에게 전부 싱글톤 구현을 위한 코드를 넣어주어야해서 코드가 지저분해짐.
        //또한 한개의 인스턴스를 get 구현 메서드에 의존하게됨. 이는 추상화에의존해야한다는 DIP를 위반함.
        //DIP를 위반하니 마찬가지로 OCP 또한 위반할 가능성이 높음.
    void singletonShot() {
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();
        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤 ")
    // 스프링 DI컨테이너는 위에서 말한 싱글톤패턴의 단점을 잡고 장점만 살리는 방법임.
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
