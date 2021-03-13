package men.suruceanu.exchange.repositories;

import men.suruceanu.exchange.dao.ExchangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface ExchangeHistoryRepository extends JpaRepository<ExchangeHistory, Long> {
}
