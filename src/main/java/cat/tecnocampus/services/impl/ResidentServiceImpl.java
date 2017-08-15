package cat.tecnocampus.services.impl;

import cat.tecnocampus.domain.Resident;
import cat.tecnocampus.respositories.ResidentRepository;
import cat.tecnocampus.respositories.UserRepositoy;
import cat.tecnocampus.services.CommunityService;
import cat.tecnocampus.services.ResidentService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by vil883 on 23/04/2017.
 */
@Service
public class ResidentServiceImpl implements ResidentService {

    private ResidentRepository residentRepository;
    private DataSource datasource;
    private UserRepositoy userRepositoy;

    @Autowired
    public ResidentServiceImpl(ResidentRepository residentRepository, DataSource datasource, UserRepositoy userRepositoy) {
        this.residentRepository = residentRepository;
        this.datasource = datasource;
        this.userRepositoy = userRepositoy;
    }

    @Override
    public Iterable<Resident> listAllResident() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("ADMIN")){
                return residentRepository.findAll();
            }
        }

        Resident currentResident = residentRepository.findByEmail(currentUser);
        return residentRepository.findByCommunity(currentResident.getCommunity());
    }

    @Override
    public Resident save(Resident resident) {
        createUser(resident);
        return residentRepository.save(resident);
    }

    @Override
    public Resident getResidentById(Integer id) {
        return residentRepository.findOne(id);
    }

    private void createUser(Resident resident) {
        if(userRepositoy.findOne(resident.getEmail())==null){
            JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
            userDetailsService.setDataSource(datasource);

            PasswordEncoder encoder = new BCryptPasswordEncoder();

            List<GrantedAuthority> authorities = new ArrayList<>();
            //authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            if(resident.getPresident()==true) authorities.add(new SimpleGrantedAuthority("ROLE_PRESIDENT"));
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            User userDetails = new User(resident.getEmail(), encoder.encode("1234"), authorities);

            userDetailsService.createUser(userDetails);
        }
    }

}
