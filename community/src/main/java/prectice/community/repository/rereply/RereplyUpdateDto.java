package prectice.community.repository.rereply;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import prectice.community.domain.Rereply;

@Data
@Getter @Setter
public class RereplyUpdateDto {

    private String content;

    public RereplyUpdateDto() {}

    public RereplyUpdateDto(String content) {
        this.content = content;
    }
}
