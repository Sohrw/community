package prectice.community.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    private Long boardId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String title;
    private String content;
    private String registerDate;
    private String updateDate;
    private String deleteDate;

    public Board() {
    }

    public Board(Long boardId, Member member, String title, String content, String registerDate, String updateDate, String deleteDate) {
        this.boardId = boardId;
        this.member = member;
        this.title = title;
        this.content = content;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
    }
}
