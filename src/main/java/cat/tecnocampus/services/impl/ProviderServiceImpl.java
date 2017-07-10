package cat.tecnocampus.services.impl;

import cat.tecnocampus.domain.Provider;
import cat.tecnocampus.respositories.ProviderRepository;
import cat.tecnocampus.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by internet-manager on 11/04/17.
 */
@Service
public class ProviderServiceImpl implements ProviderService {
    private ProviderRepository ProviderRepository;

    @Autowired
    public ProviderServiceImpl(ProviderRepository ProviderRepository) {
        this.ProviderRepository = ProviderRepository;
    }

    @Override
    public Iterable<Provider> listAllProvider() {
        return ProviderRepository.findAll();
    }

    @Override
    public Provider save(Provider Provider) {
        return ProviderRepository.save(Provider);
    }

    @Override
    public Provider getProviderById(Integer id) {
        return ProviderRepository.findOne(id);
    }
}
