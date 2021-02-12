package pure_Java_core.pure_core.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);

}
