package pure_Java_core.pure_core.member;

public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository; // DIP 준수

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; // 생성자 주입
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
