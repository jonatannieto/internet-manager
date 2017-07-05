package cat.tecnocampus.services.impl;

import cat.tecnocampus.domain.Resident;
import cat.tecnocampus.respositories.ResidentRepository;
import cat.tecnocampus.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vil883 on 23/04/2017.
 */
@Service
public class ResidentImpl implements ResidentService {

    private ResidentRepository residentRepository;

    @Autowired
    public ResidentImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public Iterable<Resident> listAllResident() {
        return residentRepository.findAll();
    }

    @Override
    public Resident save(Resident resident) {
        return residentRepository.save(resident);
    }

    @Override
    public Resident getResidentById(Integer id) {
        return residentRepository.findOne(id);
    }

}
