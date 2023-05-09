package prectice.community.repository.board;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import prectice.community.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Transactional
    @Modifying
    @Query("delete from Board b")
    void deleteFirstBy();

    Page<Board> findAll(Pageable pageable);

    Page<Board> findByTitleContaining(String title, Pageable pageable);

    Page<Board> findByContentContaining(String content, Pageable pageable);

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

}
