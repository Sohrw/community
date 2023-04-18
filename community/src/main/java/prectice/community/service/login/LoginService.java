package prectice.community.service.login;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prectice.community.domain.Member;
import prectice.community.repository.member.MemberRepositoryImpl;
import prectice.community.repository.member.MemberSearchCond;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepositoryImpl memberRepositoryImpl;

    public Member login(String loginId, String password) {
        return memberRepositoryImpl.findMemberForLogin(loginId, password);
    }


}
