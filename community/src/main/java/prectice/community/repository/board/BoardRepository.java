package prectice.community.repository.board;


import org.springframework.data.jpa.repository.JpaRepository;
import prectice.community.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
