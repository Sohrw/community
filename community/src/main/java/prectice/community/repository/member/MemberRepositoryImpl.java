package prectice.community.repository.member;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import prectice.community.domain.Member;
import prectice.community.domain.QMember;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static prectice.community.domain.QMember.*;

@Repository
@Transactional
public class MemberRepositoryImpl {

    private final JPAQueryFactory query;

    public MemberRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Member> findAll(MemberSearchCond cond) {
        return query.select(member)
                .from(member)
                .where(
                        likeMemberId(cond.getMemberId()),
                        likeNickname(cond.getNickname()),
                        likeMemberPassword(cond.getPassword())
                )
                .fetch();
    }

    public Boolean findMemberForDuplicate(String memberId) {
        Member members = query.select(member)
                .from(member)
                .where(
                        equalMemberId(memberId)
                )
                .fetchOne();
        if (members == null) {
            return true;
        } else {
            return false;
        }
    }

    public Member findMemberForLogin(String memberId, String password) {
        Member member1 = query.select(QMember.member)
                .from(QMember.member)
                .where(
                        equalMemberId(memberId),
                        equalMemberPassword(password))
                .fetchOne();

        return member1;
    }

    public Member findMemberWithMemberId(String memberId) {
        Member member1 = query.select(member)
                .from(member)
                .where(
                        equalMemberId(memberId)
                ).fetchOne();
        return member1;
    }

    public BooleanExpression equalMemberId(String memberId) {
        if (StringUtils.hasText(memberId)) {
            return member.memberId.eq(memberId);
        }
        return null;
    }

    public BooleanExpression equalMemberPassword(String memberPassword) {
        if (StringUtils.hasText(memberPassword)) {
            return member.memberPassword.eq(memberPassword);
        }
        return null;
    }




    public BooleanExpression likeMemberId(String memberId) {
        if (StringUtils.hasText(memberId)) {
            return member.memberId.like("%" + memberId + "%");
        }
        return null;
    }

    public BooleanExpression likeMemberPassword(String memberPassword) {
        if (StringUtils.hasText(memberPassword)) {
            return member.memberPassword.like("%" + memberPassword + "%");
        }
        return null;
    }

    public BooleanExpression likeNickname(String nickname) {
        if (StringUtils.hasText(nickname)) {
            return member.nickname.like("%" + nickname + "%");
        }
        return null;
    }




}
