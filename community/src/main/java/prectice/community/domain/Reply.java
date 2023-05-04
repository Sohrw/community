package prectice.community.domain;

import lombok.Data;
import org.apache.ibatis.annotations.One;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Reply {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    private Board board;

    @Id @GeneratedValue
    private Long replyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member replyWriter;

    @OneToMany(mappedBy = "originReply")
    private List<Rereply> rereplyList;
    private String replyContent;
    private String registerDate;
    private String updateDate;
    private String deleteDate;

    public Reply() {}

    public Reply
            (Board board,
             Long replyId,
             Member replyWriter,
             String replyContent,
             String registerDate,
             String updateDate,
             String deleteDate) {
        this.board = board;
        this.replyId = replyId;
        this.replyWriter = replyWriter;
        this.replyContent = replyContent;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
    }
}
