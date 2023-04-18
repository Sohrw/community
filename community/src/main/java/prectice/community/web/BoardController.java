package prectice.community.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import prectice.community.domain.Board;
import prectice.community.domain.Member;
import prectice.community.repository.board.BoardSearchCond;
import prectice.community.repository.board.BoardUpdateDto;
import prectice.community.service.board.BoardService;
import prectice.community.service.board.BoardServiceImpl;
import prectice.community.service.login.LoginService;
import prectice.community.service.member.MemberService;
import prectice.community.service.member.MemberServiceImpl;
import prectice.community.web.session.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final LoginService loginService;
    private final BoardServiceImpl boardServiceImpl;
    private final SessionManager sessionManager;
    private final MemberService memberService;
    private final MemberServiceImpl memberServiceImpl;

    @GetMapping
    public String boards(@ModelAttribute("boardSearch") BoardSearchCond boardSearchCond, Model model, HttpServletRequest request) {
        List<Board> boards = boardService.findBoards(boardSearchCond);
        HttpSession session = request.getSession();

        model.addAttribute("loginMember", session.getAttribute(SessionConst.LOGIN_MEMBER));
        model.addAttribute("boards", boards);
        return "boards";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, Model model) {
        Board board = boardService.findById(boardId).get();
        model.addAttribute("board", board);
        return "board";
    }

    @GetMapping("/add")
    public String writeForm(Model model) {
        model.addAttribute("form", new BoardSearchCond());
        return "addForm";
    }

    @PostMapping("/add")
    public String writeBoard(@ModelAttribute Board board, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        Board notSaveBoard = new Board();


        notSaveBoard.setBoardId(board.getBoardId());

        notSaveBoard.setTitle(board.getTitle());
        notSaveBoard.setContent(board.getContent());
        HttpSession session = request.getSession();
        Member attribute = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        notSaveBoard.setMember(attribute);
        Board board1 = boardService.save(notSaveBoard);
        redirectAttributes.addAttribute("boardId", board1.getBoardId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model) {
        Board board = boardService.findById(boardId).get();
        model.addAttribute("board", board);
        return "editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable Long boardId, @ModelAttribute BoardUpdateDto boardUpdateDto) {
        boardService.update(boardId, boardUpdateDto);
        return "redirect:/boards/{boardId}";
    }
}
