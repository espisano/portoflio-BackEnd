package com.portfolioargentinaprograma.ernesto.salvador.pisano.Repository;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
 public interface IPersonRepository extends JpaRepository<Person, Long> {
    
}
