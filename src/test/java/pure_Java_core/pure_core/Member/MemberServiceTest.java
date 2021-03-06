package pure_Java_core.pure_core.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pure_Java_core.pure_core.AppConfig;
import pure_Java_core.pure_core.member.Grade;
import pure_Java_core.pure_core.member.Member;
import pure_Java_core.pure_core.member.MemberService;
import pure_Java_core.pure_core.member.MemberServiceImpl;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void BeforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }


    @Test
    void join() {
        //given
        Member member = new Member(1L, "MemberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
