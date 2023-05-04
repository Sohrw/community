package prectice.community.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Rereply {

    @Id @GeneratedValue
    private Long rereplyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member rereplyMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id")
    private Reply originReply;

    private String rereplyContent;
    private String registerDate;
    private String updateDate;
    private String deleteDate;

    public Rereply() {
    }

    public Rereply(Long rereplyId, Member rereplyMember, String rereplyContent, String registerDate, String updateDate, String deleteDate) {
        this.rereplyId = rereplyId;
        this.rereplyMember = rereplyMember;
        this.rereplyContent = rereplyContent;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
    }
}
