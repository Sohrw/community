package prectice.community.repository.rereply;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class RereplySearchCond {

    private String rereplyContent;
    private Long replyId;

    public RereplySearchCond() {

    }

    public RereplySearchCond(String rereplyContent, Long replyId) {
        this.rereplyContent = rereplyContent;
        this.replyId = replyId;
    }
}
