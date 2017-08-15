package cat.tecnocampus.services.impl;

import cat.tecnocampus.domain.Community;
import cat.tecnocampus.domain.Contract;
import cat.tecnocampus.domain.Resident;
import cat.tecnocampus.respositories.CommunityRepository;
import cat.tecnocampus.respositories.ResidentRepository;
import cat.tecnocampus.services.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by internet-manager on 11/04/17.
 */
@Service
public class CommunityServiceImpl implements CommunityService {
    private CommunityRepository communityRepository;
    private ResidentRepository residentRepository;

    @Autowired
    public CommunityServiceImpl(CommunityRepository communityRepository, ResidentRepository residentRepository) {
        this.communityRepository = communityRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    public Iterable<Community> listAllCommunity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("ADMIN")){
                return communityRepository.findAll();
            }
        }

        Resident currentResident = residentRepository.findByEmail(currentUser);
        List<Community> communityList = new ArrayList<>();
        communityList.add(currentResident.getCommunity());

        return communityList;
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

    @Override
    public void addContract(Integer communityId, Contract contract) {
        Community community = communityRepository.findOne(communityId);
        community.addContract(contract);
        communityRepository.save(community);
    }
}
