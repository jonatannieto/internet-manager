package cat.tecnocampus.services.impl;

import cat.tecnocampus.domain.Contract;
import cat.tecnocampus.respositories.ContractRepository;
import cat.tecnocampus.services.CommunityService;
import cat.tecnocampus.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jonatan on 10/07/2017.
 */
@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private CommunityService communityService;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository, CommunityService communityService) {
        this.contractRepository = contractRepository;
        this.communityService = communityService;
    }

    @Override
    public Iterable<Contract> listAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public Contract save(Contract contract) {
        contractRepository.save(contract);
        if(null!=contract.getCommunityId()){
            communityService.addContract(contract.getCommunityId(), contract);
        }

        return contract;
    }

    @Override
    public Contract getContractById(Integer id) {
        return contractRepository.findOne(id);
    }
}
