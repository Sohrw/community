package prectice.community.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRereply is a Querydsl query type for Rereply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRereply extends EntityPathBase<Rereply> {

    private static final long serialVersionUID = 1476467785L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRereply rereply = new QRereply("rereply");

    public final StringPath deleteDate = createString("deleteDate");

    public final QReply originReply;

    public final StringPath registerDate = createString("registerDate");

    public final StringPath rereplyContent = createString("rereplyContent");

    public final NumberPath<Long> rereplyId = createNumber("rereplyId", Long.class);

    public final NumberPath<Integer> rereplyLike = createNumber("rereplyLike", Integer.class);

    public final QMember rereplyMember;

    public final StringPath updateDate = createString("updateDate");

    public QRereply(String variable) {
        this(Rereply.class, forVariable(variable), INITS);
    }

    public QRereply(Path<? extends Rereply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRereply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRereply(PathMetadata metadata, PathInits inits) {
        this(Rereply.class, metadata, inits);
    }

    public QRereply(Class<? extends Rereply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.originReply = inits.isInitialized("originReply") ? new QReply(forProperty("originReply"), inits.get("originReply")) : null;
        this.rereplyMember = inits.isInitialized("rereplyMember") ? new QMember(forProperty("rereplyMember")) : null;
    }

}

