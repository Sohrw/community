package prectice.community.repository.rereply;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import prectice.community.domain.QRereply;
import prectice.community.domain.Rereply;

import javax.persistence.EntityManager;
import java.util.List;

import static prectice.community.domain.QRereply.*;

@Repository
@Transactional
public class RereplyRepositoryImpl {

    private final JPAQueryFactory query;

    public RereplyRepositoryImpl(EntityManager entityManager) {
        this.query = new JPAQueryFactory(entityManager);
    }

    public List<Rereply> findAll(RereplySearchCond cond) {
        List<Rereply> fetch = query.select(rereply)
                .from(rereply)
                .where(
                        replyIdEq(cond.getReplyId())
                )
                .fetch();

        return fetch;

    }

    public BooleanExpression replyIdEq(Long replyId) {
        return replyId == null ? null : rereply.originReply.replyId.eq(replyId);
    }
}
