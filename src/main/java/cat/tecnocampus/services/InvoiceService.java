package cat.tecnocampus.services;

import cat.tecnocampus.domain.Contract;
import cat.tecnocampus.domain.Invoice;

/**
 * Created by Jonatan on 19/07/2017.
 */
public interface InvoiceService {

    Iterable<Invoice> listAllInvoices();

    Invoice save(Invoice invoice);

    Invoice getInvoiceById(Integer id);

    String createInvoiceStack(Contract contract);
}
