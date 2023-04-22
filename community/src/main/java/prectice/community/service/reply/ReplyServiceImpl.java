package prectice.community.service.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prectice.community.domain.Reply;
import prectice.community.repository.reply.ReplyRepository;
import prectice.community.repository.reply.ReplyRepositoryImpl;
import prectice.community.repository.reply.ReplySearchCond;
import prectice.community.repository.reply.ReplyUpdateDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService{
    private final ReplyRepository replyRepository;
    private final ReplyRepositoryImpl replyRepositoryImpl;

    @Override
    public Reply save(Reply reply) {
        reply.setRegisterDate(LocalDateTime.now().toString());
        return replyRepository.save(reply);
    }

    @Override
    public void update(Long replyId, ReplyUpdateDto updateParam) {
        Reply findReply = findById(replyId).orElseThrow();
        findReply.setUpdateDate(LocalDateTime.now().toString());
        findReply.setReplyContent(updateParam.getContent());
    }

    @Override
    public Optional<Reply> findById(Long id) {
        return replyRepository.findById(id);
    }

    @Override
    public List<Reply> findReply(ReplySearchCond cond) {
        return replyRepositoryImpl.findAll(cond);
    }

}
