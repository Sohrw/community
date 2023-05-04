package prectice.community.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberId;
    private String memberPassword;
    private String nickname;
    private String emailAddress;
    private String joinDate;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "replyWriter")
    @JsonIgnore
    private List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "rereplyMember")
    @JsonIgnore
    private List<Rereply> rereplyList = new ArrayList<>();
    public Member() {

    }

    public Member(Long id, String memberId, String memberPassword, String nickname, String emailAddress, String joinDate) {
        this.id = id;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.nickname = nickname;
        this.emailAddress = emailAddress;
        this.joinDate = joinDate;
    }
}
