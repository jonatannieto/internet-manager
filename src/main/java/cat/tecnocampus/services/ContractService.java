package cat.tecnocampus.services;

import cat.tecnocampus.domain.Contract;

/**
 * Created by Internet-Manager on 10/07/2017.
 */
public interface ContractService {
    Iterable<Contract> listAllContracts();

    Contract save(Contract contract);

    Contract getContractById(Integer id);
}
