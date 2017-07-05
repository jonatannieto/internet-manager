package cat.tecnocampus.services.impl;

import cat.tecnocampus.domain.LandAgent;
import cat.tecnocampus.respositories.CommunityRepository;
import cat.tecnocampus.respositories.LandAgentRepository;
import cat.tecnocampus.services.LandAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vil883 on 23/04/2017.
 */

@Service
public class LandAgentImpl implements LandAgentService {

    private LandAgentRepository landAgentRepository;

    @Autowired
    public LandAgentImpl(LandAgentRepository landAgentRepository) {
        this.landAgentRepository = landAgentRepository;
    }

    @Override
    public Iterable<LandAgent> listAllLandAgent() {
        return landAgentRepository.findAll();
    }

    @Override
    public LandAgent save(LandAgent landAgent) {
        return landAgentRepository.save(landAgent);
    }

    @Override
    public LandAgent getLandAgentById(Integer id) {
        return landAgentRepository.findOne(id);
    }
}
