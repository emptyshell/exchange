package men.suruceanu.exchange.repositories;

import men.suruceanu.exchange.dao.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeLogin(String employeeLogin);
    Boolean existsByEmployeeLogin(String employeeLogin);
}
