package cat.tecnocampus.bootstrap;

import cat.tecnocampus.domain.LandAgent;
import cat.tecnocampus.respositories.LandAgentRepository;
import cat.tecnocampus.respositories.ResidentRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by internet-manager on 29/3/17.
 */
@Component
public class LandAgentLoader implements ApplicationListener<ContextRefreshedEvent> {
    private LandAgentRepository landAgentRepository;
    private ResidentRepository residentRepository;
    private Logger log = Logger.getLogger(LandAgentLoader.class);

    @Autowired
    public LandAgentLoader(ResidentRepository residentRepository, LandAgentRepository landAgentRepository) {
        this.landAgentRepository = landAgentRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {




        LandAgent landAgent1 = new LandAgent( "47475225F", "Jordi", "Mas", "Martinez", "934444552", "jordi@api.com", "934445525");
        LandAgent landAgent2 = new LandAgent( "00000073B", "David", "Vidal", "Marti", "934446552", "david@agent.com", "934445225");

        log.info("Saving landAgent " + landAgent1.getName());
        landAgentRepository.save(landAgent1);
        log.info("Saving landAgent " + landAgent2.getName());
        landAgentRepository.save(landAgent2);
    }
}
