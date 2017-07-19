package cat.tecnocampus.controllers;

import cat.tecnocampus.domain.Contract;
import cat.tecnocampus.domain.Invoice;
import cat.tecnocampus.services.InvoiceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Jonatan on 19/07/2017.
 */
@Controller
public class InvoiceController {
    private InvoiceService invoiceService;

    private Logger log = Logger.getLogger(InvoiceController.class);

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /* RequestMapping indica la uri(path) que invoca este método y de que [tipo] debe ser */
    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.addObject("invoices", invoiceService.listAllInvoices());
        modelAndView.setViewName("invoices");
        log.info("Returning invoices.");
        return modelAndView;
    }

    /* PathVariable indica que Spring va a coger del path un valor, en este caso el id */
    @RequestMapping("invoice/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("invoice", invoiceService.getInvoiceById(id));
        log.info("Returning invoice: " + id);
        return "invoiceshow";
    }

    @RequestMapping(value = "invoice/new")
    public String newInvoice(Model model){
        model.addAttribute("invoice",  new Invoice());

        return "invoiceform";
    }

    @RequestMapping(value = "invoice/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("invoice", invoiceService.getInvoiceById(id));

        return "invoiceform";
    }

    @RequestMapping(value = "invoice", method = RequestMethod.POST )
    public String create(Invoice invoice){
        invoiceService.save(invoice);

        return "redirect:/invoice/" + invoice.getId();
    }

    @RequestMapping(value = "/invoices/generate", method = RequestMethod.POST)
    public ModelAndView generateInvoicesForContact(Contract contract, ModelAndView modelAndView){
        modelAndView.addObject("contract", contract);
        modelAndView.addObject("message", invoiceService.createInvoiceStack(contract));
        modelAndView.setViewName("contractshow");

        return modelAndView;
    }
}
