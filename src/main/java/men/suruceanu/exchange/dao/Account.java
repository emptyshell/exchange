package men.suruceanu.exchange.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private Long accountId;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cur_id", referencedColumnName = "cur_id")
    @NotNull
    private Currency currencyId;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "br_id", referencedColumnName = "br_id")
    @NotNull
    private Branch branchId;

    @Column(name = "ac_amount")
    @NotNull
    private double accountAmount;

    @Column(name = "timestamp")
    @NotNull
    private LocalDateTime timestamp;
}
