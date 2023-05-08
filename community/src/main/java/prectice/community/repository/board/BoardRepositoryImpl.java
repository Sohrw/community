package prectice.community.repository.board;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import prectice.community.domain.Board;
import prectice.community.domain.QBoard;

import javax.persistence.EntityManager;
import java.util.List;

import static prectice.community.domain.QBoard.*;

@Repository
@Transactional
public class BoardRepositoryImpl{
    private final JPAQueryFactory query;

    public BoardRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Board> findAll(BoardSearchCond cond) {
        return query.select(board)
                .from(board)
                .where(
                        likeTitle(cond.getTitle())
                ).fetch();
    }

    private BooleanExpression likeTitle(String title) {
        if (StringUtils.hasText(title)) {
            return board.title.like("%" + title + "%");
        }
        return null;
    }




}
