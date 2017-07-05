package cat.tecnocampus.controllers;

import cat.tecnocampus.domain.LandAgent;
import cat.tecnocampus.services.LandAgentService;
import cat.tecnocampus.services.LandAgentService;
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
public class LandAgentController {
    private LandAgentService landAgentService;

    private Logger log = Logger.getLogger(LandAgentController.class);

    /* La inyección de dependencias hacen que entre las capas no se acople el código, el FW ya se encarga de inyectar el
     * servicio que toca en cada caso, seguimos la D de los principios SOLID, donde inyectamos interfaces, no implementaciones.*/
    @Autowired
    public LandAgentController(LandAgentService landAgentService) {
        this.landAgentService = landAgentService;
    }

    /* RequestMapping indica la uri(path) que invoca este método y de que [tipo] debe ser */
    @RequestMapping(value = "/landAgents", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.addObject("landAgents", landAgentService.listAllLandAgent());
        modelAndView.setViewName("landAgents");
        log.info("Returning landAgents.");
        return modelAndView;
    }

    /* PathVariable indica que Spring va a coger del path un valor, en este caso el id */
    @RequestMapping("landAgent/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("landAgent", landAgentService.getLandAgentById(id));
        log.info("Returning landAgent: " + id);
        return "landAgentshow";
    }

    @RequestMapping(value = "landAgent/new")
    public String newInvoice(Model model){
        model.addAttribute("landAgent", landAgentService.listAllLandAgent());
        return "landAgentShow";
    }

    @RequestMapping(value = "landAgent/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("landAgent", landAgentService.getLandAgentById(id));
        return "landAgentform";
    }

    @RequestMapping(value = "landAgent", method = RequestMethod.POST)
    public String create(LandAgent landAgent){
        landAgentService.save(landAgent);
        return "redirect:/landAgent/" + landAgent.getId();
    }
}
