package prectice.community.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Reply {

    private Long boardId;

    @Id @GeneratedValue
    private Long replyId;
    private String replyWriter;
    private String replyContent;
    private String registerDate;
    private String updateDate;
    private String deleteDate;

    public Reply() {}

    public Reply
            (Long boardId,
             Long replyId,
             String replyWriter,
             String replyContent,
             String registerDate,
             String updateDate,
             String deleteDate) {
        this.boardId = boardId;
        this.replyId = replyId;
        this.replyWriter = replyWriter;
        this.replyContent = replyContent;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
    }
}
