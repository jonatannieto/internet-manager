package cat.tecnocampus.services.impl;

import cat.tecnocampus.domain.Contract;
import cat.tecnocampus.respositories.ContractRepository;
import cat.tecnocampus.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jonatan on 10/07/2017.
 */
@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Iterable<Contract> listAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract getContractById(Integer id) {
        return contractRepository.findOne(id);
    }
}
