package pure_Java_core.pure_core.member;

public class MemberServiceImpl implements MemberService {
    private final MemberRepository repository = new MemberMemoryRepository();

    @Override
    public void join(Member member) {
        repository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return repository.findById(memberId);
    }
}
