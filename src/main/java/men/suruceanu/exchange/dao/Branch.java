package men.suruceanu.exchange.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
    private Long branchId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "br_local_currency", referencedColumnName = "cur_id")
    private Currency branchLocalCurrency;

    @Column(name = "br_address")
    private String branchAddress;

    @Column(name = "br_city")
    private String branchCity;

    @Column(name = "br_country")
    private String branchCountry;

    @Column(name = "br_zip")
    private String branchZip;
}
