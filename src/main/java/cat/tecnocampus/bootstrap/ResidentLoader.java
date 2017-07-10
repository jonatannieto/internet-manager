package cat.tecnocampus.bootstrap;

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
public class ResidentLoader implements ApplicationListener<ContextRefreshedEvent> {
    private LandAgentRepository landAgentRepository;
    private ResidentRepository residentRepository;
    private Logger log = Logger.getLogger(ResidentLoader.class);

    @Autowired
    public ResidentLoader(ResidentRepository residentRepository, LandAgentRepository landAgentRepository) {
        this.landAgentRepository = landAgentRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

      /*  Resident resident1 = new Resident( "47475225F", "Jordi", "Mas", "Martinez", "1", "2", "B",  "934445525", "jordi@api.com" );
        Resident resident2 = new Resident( "47475229P", "Juan", "Eloy", "Marquez", "3", "5", "A", "934423525" ,  "eloy@api.com");


        log.info("Saving resident " + resident1.getName());
        residentRepository.save(resident1);

        log.info("Saving resident " + resident2.getName());
        residentRepository.save(resident2);*/
    }
}
