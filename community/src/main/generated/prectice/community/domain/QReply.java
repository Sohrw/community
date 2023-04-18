package prectice.community.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReply is a Querydsl query type for Reply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReply extends EntityPathBase<Reply> {

    private static final long serialVersionUID = -315783396L;

    public static final QReply reply = new QReply("reply");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath deleteDate = createString("deleteDate");

    public final StringPath registerDate = createString("registerDate");

    public final StringPath replyContent = createString("replyContent");

    public final NumberPath<Long> replyId = createNumber("replyId", Long.class);

    public final StringPath replyWriter = createString("replyWriter");

    public final StringPath updateDate = createString("updateDate");

    public QReply(String variable) {
        super(Reply.class, forVariable(variable));
    }

    public QReply(Path<? extends Reply> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReply(PathMetadata metadata) {
        super(Reply.class, metadata);
    }

}

