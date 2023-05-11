package prectice.community.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    private Long boardId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotBlank
    @Size(max = 30)
    private String title;
    private String content;
    private String registerDate;
    private String updateDate;
    private String deleteDate;
    private int boardViewCount;
    private int boardLike;


    @OneToMany(mappedBy = "board")
    @JsonIgnore
    public List<Reply> replyList;

    public Board() {
    }

    public Board(Long boardId, Member member, String title, String content, String registerDate, String updateDate, String deleteDate, int like) {
        this.boardId = boardId;
        this.member = member;
        this.title = title;
        this.content = content;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
        this.boardLike = like;
    }
}
