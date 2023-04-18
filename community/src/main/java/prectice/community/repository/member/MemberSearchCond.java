package prectice.community.repository.member;

import lombok.Data;

@Data
public class MemberSearchCond {
    private String nickname;
    private String memberId;
    private String password;
}
