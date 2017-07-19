package cat.tecnocampus.services.impl;

import cat.tecnocampus.domain.Community;
import cat.tecnocampus.domain.Contract;
import cat.tecnocampus.domain.Invoice;
import cat.tecnocampus.domain.Resident;
import cat.tecnocampus.respositories.InvoiceRepository;
import cat.tecnocampus.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Jonatan on 19/07/2017.
 */
@Service
public class InvoiceServiceImpl implements InvoiceService{
    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Iterable<Invoice> listAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoiceById(Integer id) {
        return invoiceRepository.findOne(id);
    }

    @Override
    public String createInvoiceStack(Contract contract){
        List<Invoice> invoiceList = new ArrayList<>();
        for (Resident resident : contract.getCommunity().getResidentList()) {
            Date date = new Date(Calendar.getInstance().getTime().getTime());

            Invoice newInvoice = new Invoice(date, contract, resident, contract.getMonthlyPrice()/contract.getCommunity().getResidentList().size(), 21.0);

            invoiceList.add(newInvoice);
        }

        invoiceRepository.save(invoiceList);
        return "Invoices have been created successfully.";
    }
}
