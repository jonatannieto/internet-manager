package cat.tecnocampus.respositories;

import cat.tecnocampus.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Internet-Manager on 10/07/2017.
 */
public interface ContractRepository extends JpaRepository<Contract, Integer>, JpaSpecificationExecutor {}
