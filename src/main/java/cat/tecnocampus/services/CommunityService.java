package cat.tecnocampus.services;

import cat.tecnocampus.domain.Community;
import cat.tecnocampus.domain.Resident;

/**
 * Created by internet-manager on 11/04/17.
 */
public interface CommunityService {

    Iterable<Community> listAllCommunity();

    Community save(Community community);

    Community getCommunityById(Integer id);

    void addResident(Integer id, Resident resident);
}
