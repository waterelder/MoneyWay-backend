package xyz.trackbuck.domain.model.common;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lex on 08.10.16.
 */

@Getter
@Setter
public class BooleanResponse {

    public Boolean status;

    public BooleanResponse(Boolean status) {
        this.status = status;
    }

}
