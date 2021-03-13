package men.suruceanu.exchange.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "exchange")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchange_id")
    @NotNull
    private Long exchangeId;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cur_id", referencedColumnName = "cur_id")
    @NotNull
    private Currency currency;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "br_id", referencedColumnName = "br_id")
    @NotNull
    private Branch branchId;

    @Column(name = "buy_price")
    @NotNull
    private double buyPrice;

    @Column(name = "sell_price")
    @NotNull
    private double sellPrice;

    @Column(name = "rate")
    @NotNull
    private long rate;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date date;

    @Column(name = "timestamp")
    @NotNull
    private LocalDateTime timestamp;

}
