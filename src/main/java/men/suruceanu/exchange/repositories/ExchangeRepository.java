package men.suruceanu.exchange.repositories;

import men.suruceanu.exchange.dao.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    @Query("select ex from Exchange ex where " +
            "ex.currency.name=:currencyName and " +
            "ex.branchId.branchId=:branchId and " +
            "ex.date=:date")
    Exchange findByBranchIdAndCurrencyName(@Param("branchId") Long branchId, @Param("currencyName") String currencyName, @Param("date") Date date);

    @Query("select count(ex) > 0 from Exchange ex where ex.date=:date and ex.currency.name=:currencyName")
    Boolean existsByDateAndCurrencyName(@Param("date") Date date, @Param("currencyName") String currencyName);
}
