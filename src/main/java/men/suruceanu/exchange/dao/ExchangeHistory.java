package men.suruceanu.exchange.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "branch")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ExchangeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ex_history_id")
    private Long exchangeHistoryId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exchange_id", referencedColumnName = "exchange_id")
    private Exchange exchangeId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "br_id", referencedColumnName = "br_id")
    private Branch branchId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private Employee employeeId;

    @Column(name = "buy_amount")
    private double buyPrice;

    @Column(name = "sell_amount")
    private double sellPrice;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

}
