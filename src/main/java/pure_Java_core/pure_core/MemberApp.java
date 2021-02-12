package pure_Java_core.pure_core;

import pure_Java_core.pure_core.member.Grade;
import pure_Java_core.pure_core.member.Member;
import pure_Java_core.pure_core.member.MemberService;
import pure_Java_core.pure_core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "MemberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
