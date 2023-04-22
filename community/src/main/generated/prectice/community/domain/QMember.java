package prectice.community.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1342595928L;

    public static final QMember member = new QMember("member1");

    public final ListPath<Board, QBoard> boards = this.<Board, QBoard>createList("boards", Board.class, QBoard.class, PathInits.DIRECT2);

    public final StringPath emailAddress = createString("emailAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath joinDate = createString("joinDate");

    public final StringPath memberId = createString("memberId");

    public final StringPath memberPassword = createString("memberPassword");

    public final StringPath nickname = createString("nickname");

    public final ListPath<Reply, QReply> replies = this.<Reply, QReply>createList("replies", Reply.class, QReply.class, PathInits.DIRECT2);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

