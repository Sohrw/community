package prectice.community.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(min = 4, message = "아이디는 최소 4자 이상이어야 합니다.")
    private String memberId;

    @NotBlank
    @Size(min = 4, message = "비밀번호는 최소 4자 이상이어야 합니다.")
    private String memberPassword;

    @NotNull
    private String nickname;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
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
