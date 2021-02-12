package pure_Java_core.pure_core.member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
