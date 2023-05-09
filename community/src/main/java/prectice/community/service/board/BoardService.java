package prectice.community.service.board;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import prectice.community.domain.Board;
import prectice.community.repository.board.BoardSearchCond;
import prectice.community.repository.board.BoardUpdateDto;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    Board save(Board board);

    void update(Long boardId, BoardUpdateDto updateParam);

    Optional<Board> findById(Long id);

    Page<Board> findBoards(BoardSearchCond boardSearchCond, Pageable pageable);

    void delete(Long boardId);

}
