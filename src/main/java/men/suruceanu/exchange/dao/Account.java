package men.suruceanu.exchange.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ac_id")
    private Long accountId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cur_id", referencedColumnName = "cur_id")
    private Currency currencyId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "br_id", referencedColumnName = "br_id")
    private Branch branchId;

    @Column(name = "ac_amount")
    private double accountAmount;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
