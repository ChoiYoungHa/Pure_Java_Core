package pure_Java_core.pure_core.Component;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pure_Java_core.pure_core.AutoAppConfig;
import pure_Java_core.pure_core.member.MemberService;


public class BasicScan {
    @Test
    @DisplayName("컴포넌트 스캔")
    void scantest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
