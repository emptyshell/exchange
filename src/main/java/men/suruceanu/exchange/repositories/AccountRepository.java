package men.suruceanu.exchange.repositories;

import men.suruceanu.exchange.dao.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select acc from Account acc where " +
            "acc.currencyId.name=:currencyName and " +
            "acc.timestamp<=:date or acc.timestamp>=:date")
    Optional<Account> findByCurrencyNameAndTimestamp(@Param("currencyName") String currencyName, @Param("date") LocalDateTime date);

}
