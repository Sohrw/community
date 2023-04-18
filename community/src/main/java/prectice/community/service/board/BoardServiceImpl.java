package prectice.community.service.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prectice.community.domain.Board;
import prectice.community.domain.Member;
import prectice.community.repository.board.BoardRepository;
import prectice.community.repository.board.BoardRepositoryImpl;
import prectice.community.repository.board.BoardSearchCond;
import prectice.community.repository.board.BoardUpdateDto;
import prectice.community.repository.member.MemberRepositoryImpl;
import prectice.community.web.session.SessionManager;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardRepositoryImpl boardRepositoryImpl;
    private final MemberRepositoryImpl memberRepositoryImpl;

    private final SessionManager sessionManager;

    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public void update(Long boardId, BoardUpdateDto updateParam) {
        Board findBoard = findById(boardId).orElseThrow();
        findBoard.setTitle(updateParam.getTitle());
        findBoard.setContent(updateParam.getContent());
    }

    @Override
    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }


    @Override
    public List<Board> findBoards(BoardSearchCond boardSearchCond) {
        return boardRepositoryImpl.findAll(boardSearchCond);
    }
}
