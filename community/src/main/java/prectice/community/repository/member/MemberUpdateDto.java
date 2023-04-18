package prectice.community.repository.member;

import lombok.Data;

@Data
public class MemberUpdateDto {

    private String memberId;
    private String memberPassword;
    private String nickname;

    public MemberUpdateDto() {
    }

    public MemberUpdateDto(String memberId, String memberPassword, String nickname) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.nickname = nickname;
    }
}
