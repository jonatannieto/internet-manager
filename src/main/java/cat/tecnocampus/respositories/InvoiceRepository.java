package cat.tecnocampus.respositories;

import cat.tecnocampus.domain.Invoice;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jonatan on 19/07/2017.
 */
public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {}
