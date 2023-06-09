package prectice.community.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReply is a Querydsl query type for Reply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReply extends EntityPathBase<Reply> {

    private static final long serialVersionUID = -315783396L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReply reply = new QReply("reply");

    public final QBoard board;

    public final StringPath deleteDate = createString("deleteDate");

    public final StringPath registerDate = createString("registerDate");

    public final StringPath replyContent = createString("replyContent");

    public final NumberPath<Long> replyId = createNumber("replyId", Long.class);

    public final NumberPath<Integer> replyLike = createNumber("replyLike", Integer.class);

    public final QMember replyWriter;

    public final ListPath<Rereply, QRereply> rereplyList = this.<Rereply, QRereply>createList("rereplyList", Rereply.class, QRereply.class, PathInits.DIRECT2);

    public final StringPath updateDate = createString("updateDate");

    public QReply(String variable) {
        this(Reply.class, forVariable(variable), INITS);
    }

    public QReply(Path<? extends Reply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReply(PathMetadata metadata, PathInits inits) {
        this(Reply.class, metadata, inits);
    }

    public QReply(Class<? extends Reply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.replyWriter = inits.isInitialized("replyWriter") ? new QMember(forProperty("replyWriter")) : null;
    }

}

