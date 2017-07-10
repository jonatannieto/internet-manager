package cat.tecnocampus.bootstrap;

import cat.tecnocampus.domain.City;
import cat.tecnocampus.domain.Community;
import cat.tecnocampus.respositories.CityRepository;
import cat.tecnocampus.respositories.CommunityRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by internet-manager on 29/3/17.
 */
@Component
public class CommunityLoader implements ApplicationListener<ContextRefreshedEvent> {
    private CityRepository cityRepository;
    private CommunityRepository communityRepository;
    private Logger log = Logger.getLogger(CommunityLoader.class);

    @Autowired
    public CommunityLoader(CityRepository cityRepository, CommunityRepository communityRepository) {
        this.cityRepository = cityRepository;

        this.communityRepository = communityRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        City city1 = new City();
        city1.setName("Mataro");
        city1.setPostalcode("08300");
        city1.setProvince("Barcelona");

        City city2 = new City();
        city2.setName("Badalona");
        city2.setPostalcode("08917");
        city2.setProvince("Barcelona");

        /* Al guardar el objeto, ya nos creará el ID */
        cityRepository.save(city1);
        cityRepository.save(city2);

        Community community1 = new Community("90931842P", "Edificio Sorigue TCM", city1, "C/Ernest Lluch 32");
        Community community2 = new Community("58032469L", "Edificio Playa Badalona", city2, "C/Del Mar 58");

        log.info("Saving community " + community1.getName());
        communityRepository.save(community1);
        log.info("Saving community " + community2.getName());
        communityRepository.save(community2);
    }
}
