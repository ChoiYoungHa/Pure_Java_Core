package pure_Java_core.pure_core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    //싱글톤 패턴은 꼭 공유필드를 조심해서 설계해야한다.
    @Test
    void test(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(testConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA : 사용자A 10000 주문
        int threadA = statefulService1.order("item1", 10000);

        // ThreadB : 사용자B 20000 주문
        int threadB = statefulService2.order("item2", 20000);

        System.out.println("threadA = " + threadA);
        System.out.println("threadB = " + threadB);


   //     org.assertj.core.api.Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);


    }

    @Configuration
    static class testConfig {

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }

    }

}
