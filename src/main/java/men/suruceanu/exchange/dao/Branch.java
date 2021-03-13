package men.suruceanu.exchange.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "branch")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Branch implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "br_id")
    @NotNull
    private Long branchId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "br_local_currency", referencedColumnName = "cur_id")
    @NotNull
    private Currency branchLocalCurrency;

    @Column(name = "br_address")
    @NotNull
    private String branchAddress;

    @Column(name = "br_city")
    @NotNull
    private String branchCity;

    @Column(name = "br_country")
    @NotNull
    private String branchCountry;

    @Column(name = "br_zip")
    @NotNull
    private String branchZip;
}
