package prectice.community.repository.reply;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class ReplyUpdateDto {

    private String content;

    public ReplyUpdateDto() {

    }
    public ReplyUpdateDto(String content) {
        this.content = content;
    }
}
