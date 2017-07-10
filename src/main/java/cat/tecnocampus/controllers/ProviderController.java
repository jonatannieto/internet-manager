package cat.tecnocampus.controllers;

import cat.tecnocampus.domain.Provider;
import cat.tecnocampus.services.CityService;
import cat.tecnocampus.services.ProviderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by internet-manager on 11/04/17.
 */
@Controller
public class ProviderController {
    private ProviderService providerService;
    private Logger log = Logger.getLogger(ProviderController.class);

    @Autowired
    public ProviderController(ProviderService ProviderService, CityService cityService) {
        this.providerService = ProviderService;
    }

    @RequestMapping(value = "/providers", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.addObject("providers", providerService.listAllProvider());
        modelAndView.setViewName("providers");
        log.info("Returning providers.");
        return modelAndView;
    }

    @RequestMapping("provider/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("Provider", providerService.getProviderById(id));
        log.info("Returning Provider: " + id);
        return "providershow";
    }

    @RequestMapping(value = "provider/new")
    public String newInvoice(Model model){
        model.addAttribute("provider", new Provider());
        return "providerform";
    }

    @RequestMapping(value = "provider/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("provider", providerService.getProviderById(id));
        return "providerform";
    }

    @RequestMapping(value = "provider", method = RequestMethod.POST)
    public String create(Provider Provider){
        providerService.save(Provider);
        return "redirect:/provider/" + Provider.getId();
    }
}
