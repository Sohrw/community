package prectice.community.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import prectice.community.domain.Board;
import prectice.community.domain.Member;
import prectice.community.domain.Reply;
import prectice.community.repository.board.BoardSearchCond;
import prectice.community.repository.board.BoardUpdateDto;
import prectice.community.repository.reply.ReplySearchCond;
import prectice.community.repository.reply.ReplyUpdateDto;
import prectice.community.service.board.BoardService;
import prectice.community.service.board.BoardServiceImpl;
import prectice.community.service.reply.ReplyService;
import prectice.community.service.reply.ReplyServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/boards")
public class BoardController {


    private final BoardService boardService;
    private final BoardServiceImpl boardServiceImpl;
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
        BoardSearchCond boardSearchCond = new BoardSearchCond();
        model.addAttribute("form", boardSearchCond);
        return "addForm";
    }

    @PostMapping(value = "/add", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<?> writeBoard(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                                        @ModelAttribute Board board, RedirectAttributes redirectAttributes,
                                        @RequestBody Board requestBody) {
        String title = requestBody.getTitle(); // 클라이언트로부터 받은 JSON 데이터에서 "title" 필드 값을 추출
        String content = requestBody.getContent(); // 클라이언트로부터 받은 JSON 데이터에서 "content" 필드 값을 추출

        Board notSaveBoard = new Board();
        notSaveBoard.setTitle(title);
        notSaveBoard.setContent(content);
        notSaveBoard.setMember(loginMember);

        Board savedBoard = boardService.save(notSaveBoard);
        Long boardId = savedBoard.getBoardId();

        // 클라이언트로 전송할 응답 데이터에 "boardId" 필드를 추가
        Map<String, Object> response = new HashMap<>();
        response.put("boardId", boardId);

        return ResponseEntity.ok(response); // ResponseEntity를 사용하여 응답 데이터를 전송
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model) {
        Board board = boardServiceImpl.findById(boardId).get();
        model.addAttribute("board", board);
        return "editForm";
    }


    @PostMapping(value = "/{boardId}/edit", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<?> edit(@PathVariable Long boardId, @RequestBody BoardUpdateDto requestBody) {
        String title = requestBody.getTitle(); // 클라이언트로부터 받은 JSON 데이터에서 "title" 필드 값을 추출
        String content = requestBody.getContent(); // 클라이언트로부터 받은 JSON 데이터에서 "content" 필드 값을 추출

        BoardUpdateDto notUpdateBoard = new BoardUpdateDto();
        notUpdateBoard.setContent(content);
        notUpdateBoard.setTitle(title);
        boardServiceImpl.update(boardId, notUpdateBoard);
        // 응답 데이터와 상태코드 등을 ResponseEntity 객체로 감싸서 반환
        Map<String, Object> response = new HashMap<>();
        response.put("boardId", boardId);
        return ResponseEntity.ok(response);
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
