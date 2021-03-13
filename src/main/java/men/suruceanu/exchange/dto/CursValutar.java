package men.suruceanu.exchange.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CursValutar {

    private String codValuta;
    private Long rata;
    private Double curs;

}
