package prectice.community.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prectice.community.domain.Member;
import prectice.community.repository.member.MemberRepository;
import prectice.community.repository.member.MemberRepositoryImpl;
import prectice.community.repository.member.MemberSearchCond;
import prectice.community.repository.member.MemberUpdateDto;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final MemberRepositoryImpl memberRepositoryImpl;
    @Override
    public Member save(Member member) {
        if (memberRepositoryImpl.findMemberForDuplicate(member.getMemberId()) == true) {
            return memberRepository.save(member);
        }
        else {
            return null;
        }



    }

    @Override
    public void update(Long memberId, MemberUpdateDto updateParam) {
        Member findMember = findById(memberId).orElseThrow();
        findMember.setMemberId(updateParam.getMemberId());
        findMember.setMemberPassword(updateParam.getMemberPassword());
        findMember.setNickname(updateParam.getNickname());
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public Member findByMemberId(String memberId) {
        return memberRepositoryImpl.findMemberWithMemberId(memberId);
    }

    @Override
    public List<Member> findMembers(MemberSearchCond memberSearchCond) {
        return memberRepositoryImpl.findAll(memberSearchCond);
    }
}
