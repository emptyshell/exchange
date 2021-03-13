package men.suruceanu.exchange.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "currency")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Currency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cur_id")
    @NotNull
    private Long currencyId;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "full_name")
    @NotNull
    private String fullName;

}
