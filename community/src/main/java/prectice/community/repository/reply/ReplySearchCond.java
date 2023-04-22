package prectice.community.repository.reply;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import prectice.community.domain.Board;
import prectice.community.domain.Member;

@Data
@Getter @Setter
public class ReplySearchCond {
    private String replyContent;
    private Long boardId;


    public ReplySearchCond() {

    }

    public ReplySearchCond(String replyContent, Long boardId) {
        this.replyContent = replyContent;
        this.boardId = boardId;
    }
}
