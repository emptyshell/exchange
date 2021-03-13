package men.suruceanu.exchange.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SchimbValutarEditare {

    private String codValuta;
    private Double suma;
    private LocalDateTime data;
    private String utilizator;

}
