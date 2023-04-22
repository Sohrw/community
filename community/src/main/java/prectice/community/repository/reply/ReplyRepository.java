package prectice.community.repository.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prectice.community.domain.Reply;


public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
