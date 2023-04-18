package prectice.community.repository.board;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import prectice.community.domain.Member;

@Data
@Getter @Setter
public class BoardSearchCond {

    private String title;
    private String nickname;

    private String content;

    public BoardSearchCond() {


    }

    public BoardSearchCond(String title, String nickname, String content) {
        this.title = title;
        this.nickname = nickname;
        this.content = content;
    }
}
