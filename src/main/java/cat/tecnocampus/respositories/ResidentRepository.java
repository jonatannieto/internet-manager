package cat.tecnocampus.respositories;

import cat.tecnocampus.domain.Resident;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jonatan on 05/07/2017.
 */
public interface ResidentRepository extends CrudRepository<Resident, Integer> {
}
