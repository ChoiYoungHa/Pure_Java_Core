package pure_Java_core.pure_core.beanFind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pure_Java_core.pure_core.member.MemberMemoryRepository;
import pure_Java_core.pure_core.member.MemberRepository;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DuplicateAppConfig.class);


    @DisplayName("타입으로 조회 시, 같은 타입이 두개 이상 있으면 중복 오류를 발생시킨다.")
    @Test
    void findByBeanDuplication(){
     //   MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () ->
                ac.getBean(MemberRepository.class));
    }

    @DisplayName("타입으로 조회 시, 같은 타입이 두개 이상 있으면 빈이름을 지정하면 된다.")
    @Test
    void findByBeanSelect(){
        MemberRepository memberRepository1 =
                ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository1).
                isInstanceOf(MemberRepository.class);
    }

    @DisplayName("특정 타입 모두 조회하기")
    @Test // map에 저장되는 방식까지 읽음 나머지 읽고 코드 연습에 처보기
    void AllTypeFindBean(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("BeansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class DuplicateAppConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemberMemoryRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemberMemoryRepository();
        }
    }

}
