package prectice.community.repository.reply;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ReplyBoardDto {

    public Long replyId;
    public Long boardId;

    private String content;
    private String nickname;

    @QueryProjection
    public ReplyBoardDto(Long replyId, Long boardId, String content, String nickname) {
        this.replyId = replyId;
        this.boardId = boardId;
        this.content = content;
        this.nickname = nickname;
    }
}
