package men.suruceanu.exchange.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SchimbValutar {

    private String codValuta;
    private Double cursSchimb;
    private Double sumaPrimita;
    private Double sumaEliberata;
    private String utilizator;

}
