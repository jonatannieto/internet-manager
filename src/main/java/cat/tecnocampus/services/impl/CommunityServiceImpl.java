package cat.tecnocampus.services.impl;

import cat.tecnocampus.domain.Community;
import cat.tecnocampus.domain.Resident;
import cat.tecnocampus.respositories.CommunityRepository;
import cat.tecnocampus.services.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by internet-manager on 11/04/17.
 */
@Service
public class CommunityServiceImpl implements CommunityService {
    private CommunityRepository communityRepository;

    @Autowired
    public CommunityServiceImpl(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    public Iterable<Community> listAllCommunity() {
        return communityRepository.findAll();
    }

    @Override
    public Community save(Community community) {
        return communityRepository.save(community);
    }

    @Override
    public Community getCommunityById(Integer id) {
        return communityRepository.findOne(id);
    }

    @Override
    public void addResident(Integer id, Resident resident) {
        Community community = communityRepository.findOne(id);
        community.addResident(resident);
        communityRepository.save(community);
    }
}
