package be.ugent.balanceservice.persistence;

import be.ugent.balanceservice.domain.BalanceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceOrderRepository extends JpaRepository<BalanceOrder, Long> {

}
