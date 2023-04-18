package prectice.community.service.member;

import prectice.community.domain.Member;
import prectice.community.repository.member.MemberSearchCond;
import prectice.community.repository.member.MemberUpdateDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Member save(Member member);

    void update(Long memberId, MemberUpdateDto updateParam);

    Optional<Member> findById(Long id);

    List<Member> findMembers(MemberSearchCond memberSearchCond);
}
