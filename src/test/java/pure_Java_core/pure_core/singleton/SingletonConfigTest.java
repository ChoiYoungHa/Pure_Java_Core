package pure_Java_core.pure_core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pure_Java_core.pure_core.AppConfig;
import pure_Java_core.pure_core.Order.OrderServiceImpl;
import pure_Java_core.pure_core.member.MemberMemoryRepository;
import pure_Java_core.pure_core.member.MemberRepository;
import pure_Java_core.pure_core.member.MemberServiceImpl;

public class SingletonConfigTest {

    @Test
    @DisplayName("Config에 정의된대로 순수히 자바코드로 실행하면 싱글톤이 깨짐 이를 실험")
    void singletonTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberMemoryRepository memberRepository = ac.getBean("memberRepository", MemberMemoryRepository.class);


        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        //각 Appconfig의 bean들은 자바코드대로라면 전부다 new해서 새로운 객체를 생성하므로
        //싱글톤이 깨질 것 같은데 스프링 DI컨테이너는 이를 방지해줌. 증명해보면 여기 3가지 Repository가
        //전부 같은 주소를 참조하고 있음.
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);

        Assertions.assertThat(memberRepository).isSameAs(memberRepository1);
        Assertions.assertThat(memberRepository).isSameAs(memberRepository2);

    }
}
