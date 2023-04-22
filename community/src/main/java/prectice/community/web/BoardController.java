package prectice.community.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import prectice.community.domain.Board;
import prectice.community.domain.Member;
import prectice.community.domain.Reply;
import prectice.community.repository.board.BoardSearchCond;
import prectice.community.repository.board.BoardUpdateDto;
import prectice.community.repository.reply.ReplySearchCond;
import prectice.community.repository.reply.ReplyUpdateDto;
import prectice.community.service.board.BoardService;
import prectice.community.service.board.BoardServiceImpl;
import prectice.community.service.login.LoginService;
import prectice.community.service.member.MemberService;
import prectice.community.service.member.MemberServiceImpl;
import prectice.community.service.reply.ReplyService;
import prectice.community.service.reply.ReplyServiceImpl;
import prectice.community.web.session.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/boards")
public class BoardController {


    private final BoardService boardService;
    private final ReplyService replyService;
    private final ReplyServiceImpl replyServiceImpl;

    @GetMapping
    public String boards(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, @ModelAttribute("boardSearch") BoardSearchCond boardSearchCond, Model model) {
        List<Board> boards = boardService.findBoards(boardSearchCond);

        model.addAttribute("loginMember", loginMember);
        model.addAttribute("boards", boards);
        return "boards";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, @ModelAttribute ReplySearchCond replySearchCond, Model model) {
        Board board = boardService.findById(boardId).get();
        List<Reply> reply = replyService.findReply(replySearchCond);
        ReplyUpdateDto form = new ReplyUpdateDto();
        model.addAttribute("replyForm", form);
        model.addAttribute("board", board);
        model.addAttribute("reply", reply);
        return "board";
    }

    @GetMapping("/add")
    public String writeForm(Model model) {
        model.addAttribute("form", new BoardSearchCond());
        return "addForm";
    }

    @PostMapping("/add")
    public String writeBoard(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                             @ModelAttribute Board board, RedirectAttributes redirectAttributes) {
        Board notSaveBoard = new Board();


        notSaveBoard.setBoardId(board.getBoardId());

        notSaveBoard.setTitle(board.getTitle());
        notSaveBoard.setContent(board.getContent());
//        HttpSession session = request.getSession();
//        Member attribute = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//        Object session1 = sessionManager.getSession(request);

        notSaveBoard.setMember(loginMember);
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

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @PostMapping("/{boardId}/delete")
    public String delete(@PathVariable Long boardId) {
        boardService.delete(boardId);
        return "redirect:/boards";
    }


    @PostMapping("/{boardId}/reply")
    public String addReply(RedirectAttributes redirectAttributes, @PathVariable Long boardId,
                           @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                           HttpServletRequest request,
                           @ModelAttribute("replyForm") ReplyUpdateDto reply) {
        Optional<Board> findBoard = boardService.findById(boardId);
        Reply addReply = new Reply();
        addReply.setReplyContent(reply.getContent());
        addReply.setReplyWriter(loginMember);
        addReply.setBoard(findBoard.get());
        replyService.save(addReply);
        return "redirect:/boards/{boardId}/";
    }
}
