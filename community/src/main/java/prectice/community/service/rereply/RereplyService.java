package prectice.community.service.rereply;

import prectice.community.domain.Rereply;
import prectice.community.repository.rereply.RereplySearchCond;
import prectice.community.repository.rereply.RereplyUpdateDto;

import java.util.List;
import java.util.Optional;

public interface RereplyService {

    Rereply save(Rereply rereply);

    void update(Long rereplyId, RereplyUpdateDto updateDto);

    Optional<Rereply> findById(Long id);

    List<Rereply> findRereplys(RereplySearchCond cond);
}
