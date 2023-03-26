package com.portfolioargentinaprograma.ernesto.salvador.pisano.Repository;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Entity.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {

    public Optional<Person> findByName(String Name);

    public boolean existsByName(String Name);

}
