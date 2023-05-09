package prectice.community.service.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import prectice.community.domain.Board;
import prectice.community.domain.Member;
import prectice.community.repository.board.BoardRepository;
import prectice.community.repository.board.BoardRepositoryImpl;
import prectice.community.repository.board.BoardSearchCond;
import prectice.community.repository.board.BoardUpdateDto;
import prectice.community.repository.member.MemberRepositoryImpl;
import prectice.community.web.SessionConst;
import prectice.community.web.session.SessionManager;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardRepositoryImpl boardRepositoryImpl;


    @Override
    public Board save(Board board) {

        board.setRegisterDate(LocalDateTime.now().toString());

        return boardRepository.save(board);
    }

    public Page<Board> getBoards(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public void update(Long boardId, BoardUpdateDto updateParam) {
        Board findBoard = findById(boardId).orElseThrow();
        findBoard.setUpdateDate(LocalDateTime.now().toString());
        findBoard.setTitle(updateParam.getTitle());
        findBoard.setContent(updateParam.getContent());
    }

    @Override
    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }




    public Page<Board> findBoards(BoardSearchCond boardSearchCond, Pageable pageable) {
        if (StringUtils.hasText(boardSearchCond.getTitle()) && StringUtils.hasText(boardSearchCond.getContent())) {
            return boardRepository.findByTitleContainingOrContentContaining(boardSearchCond.getTitle(), boardSearchCond.getContent(), pageable);
        } else if (StringUtils.hasText(boardSearchCond.getTitle())) {
            return boardRepository.findByTitleContaining(boardSearchCond.getTitle(), pageable);
        } else if (StringUtils.hasText(boardSearchCond.getContent())) {
            return boardRepository.findByContentContaining(boardSearchCond.getContent(), pageable);
        } else {
            return boardRepository.findAll(pageable);
        }
    }

    @Override
    public void delete(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public boolean addLike(Long boardId, Member loginMember) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        if (isAlreadyLiked(loginMember, boardId)) {
            return false;
        }
        addLikedBoard(loginMember, boardId);
        board.setBoardLike(board.getBoardLike() + 1);
        boardRepository.save(board);
        return true;

    }

    public boolean isAlreadyLiked(Member loginMember, Long boardId) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        Set<Long> likedBoardIds = (Set<Long>) session.getAttribute(SessionConst.LIKED_BOARD_IDS);
        if (likedBoardIds != null && likedBoardIds.contains(boardId)) {
            return true;
        }
        return false;
    }

    public void addLikedBoard(Member loginMember, Long boardId) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession(true);
        Set<Long> likedBoardIds = (Set<Long>) session.getAttribute(SessionConst.LIKED_BOARD_IDS);
        if (likedBoardIds == null) {
            likedBoardIds = new HashSet<>();
        }
        likedBoardIds.add(boardId);
        session.setAttribute(SessionConst.LIKED_BOARD_IDS, likedBoardIds);
    }

    public void increaseViewCount(Long boardId) {
        Board board = boardRepository.findById(boardId).get();
        board.setBoardViewCount(board.getBoardViewCount() + 1);
    }

    public void decreaseViewCount(Long boardId) {
        Board board = boardRepository.findById(boardId).get();
        board.setBoardViewCount(board.getBoardViewCount() - 1);
    }
}
