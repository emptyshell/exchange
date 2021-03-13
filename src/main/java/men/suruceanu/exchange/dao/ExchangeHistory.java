package men.suruceanu.exchange.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "exchange_history")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ExchangeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ex_history_id")
    private Long exchangeHistoryId;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "exchange_id", referencedColumnName = "exchange_id")
    private Exchange exchangeId;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "br_id", referencedColumnName = "br_id")
    private Branch branchId;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private Employee employeeId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

}
