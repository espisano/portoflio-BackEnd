
package com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Repository;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Entity.Rol;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Enums.RolName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolName(RolName rolName);
    
}
