package cat.tecnocampus.services.impl;

import cat.tecnocampus.domain.Community;
import cat.tecnocampus.domain.Contract;
import cat.tecnocampus.domain.Invoice;
import cat.tecnocampus.domain.Resident;
import cat.tecnocampus.exception.InvoiceStackException;
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
    public String createInvoiceStack(Contract contract) throws InvoiceStackException {
        Boolean warnings = true;

        List<Invoice> invoiceList = new ArrayList<>();
        for (Resident resident : contract.getCommunity().getResidentList()) {
            if(!isInvoiceCreated(contract, resident)){
                Date date = new Date(Calendar.getInstance().getTime().getTime());
                Invoice newInvoice = new Invoice(date, contract, resident, contract.getMonthlyPrice()/contract.getCommunity().getResidentList().size(), 21.0);
                invoiceList.add(newInvoice);
                warnings = false;
            }
        }

        if(warnings){
            throw new InvoiceStackException("Nothing to generate.");
        }else {
            invoiceRepository.save(invoiceList);
            return "New invoices have been created successfully.";
        }
    }

    @Override
    public void invoicePay(Invoice invoice) {
        invoice.setPayed(true);
        invoiceRepository.save(invoice);
    }

    private Boolean isInvoiceCreated(Contract contract, Resident resident){
        List<Invoice> invoicesByContractAndResident = invoiceRepository.findInvoicesByContractAndResident(contract, resident);

        Date actualDate = new Date(Calendar.getInstance().getTime().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(actualDate);
        int actualMonth = cal.get(Calendar.MONTH);
        int actualYear = cal.get(Calendar.YEAR);

        for (Invoice invoice : invoicesByContractAndResident) {
            cal.setTime(invoice.getDate());
            int invoiceMonth = cal.get(Calendar.MONTH);
            int invoiceYear = cal.get(Calendar.YEAR);

            if((actualMonth == invoiceMonth) && (actualYear == invoiceYear)){
                return true;
            }
        }

        return false;
    }
}
