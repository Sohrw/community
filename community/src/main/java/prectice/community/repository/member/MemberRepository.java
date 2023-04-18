package prectice.community.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import prectice.community.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {


}
