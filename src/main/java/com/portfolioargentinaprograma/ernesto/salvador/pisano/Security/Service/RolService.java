package com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Service;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Entity.Rol;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Enums.RolName;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Repository.IRolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {

    @Autowired
    IRolRepository iRolRepository;

    public Optional<Rol> getByRolName(RolName rolName) {
        return iRolRepository.findByRolName(rolName);
    }

    public void save(Rol rol) {
        iRolRepository.save(rol);
    }
    
}
