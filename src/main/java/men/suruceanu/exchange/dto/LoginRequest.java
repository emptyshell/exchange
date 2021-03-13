package men.suruceanu.exchange.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LoginRequest implements Serializable {

    private String login;
    private String password;

}
