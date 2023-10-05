package be.ugent.balanceservice.persistence;

import be.ugent.balanceservice.domain.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Balance findByVisitorId(String visitorId);
}
