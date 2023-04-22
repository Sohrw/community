package prectice.community.service.reply;

import org.springframework.stereotype.Service;
import prectice.community.domain.Reply;
import prectice.community.repository.reply.ReplySearchCond;
import prectice.community.repository.reply.ReplyUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ReplyService {
    Reply save(Reply reply);

    void update(Long replyId, ReplyUpdateDto updateParam);

    Optional<Reply> findById(Long id);

    List<Reply> findReply(ReplySearchCond cond);
}
