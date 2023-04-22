package prectice.community.repository.reply;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * prectice.community.repository.reply.QReplyBoardDto is a Querydsl Projection type for ReplyBoardDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReplyBoardDto extends ConstructorExpression<ReplyBoardDto> {

    private static final long serialVersionUID = 444468311L;

    public QReplyBoardDto(com.querydsl.core.types.Expression<Long> replyId, com.querydsl.core.types.Expression<Long> boardId, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> nickname) {
        super(ReplyBoardDto.class, new Class<?>[]{long.class, long.class, String.class, String.class}, replyId, boardId, content, nickname);
    }

}

