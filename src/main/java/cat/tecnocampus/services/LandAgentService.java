package cat.tecnocampus.services;

import cat.tecnocampus.domain.Community;
import cat.tecnocampus.domain.LandAgent;

/**
 * Created by vil883 on 23/04/2017.
 */
public interface LandAgentService {

    Iterable<LandAgent> listAllLandAgent();

    LandAgent save(LandAgent landAgent);

    LandAgent getLandAgentById(Integer id);

}
