package cat.tecnocampus.services;

import cat.tecnocampus.domain.Community;

/**
 * Created by internet-manager on 11/04/17.
 */
public interface CommunityService {

    Iterable<Community> listAllCommunity();

    Community save(Community community);

    Community getCommunityById(Integer id);
}
