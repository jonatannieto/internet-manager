package cat.tecnocampus.services;

import cat.tecnocampus.domain.Provider;

/**
 * Created by internet-manager on 11/04/17.
 */
public interface ProviderService {

    Iterable<Provider> listAllProvider();

    Provider save(Provider provider);

    Provider getProviderById(Integer id);
}
