
package com.portfolioargentinaprograma.ernesto.salvador.pisano.Service;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Entity.Person;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Interface.IPersonService;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Repository.IPersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpPersonService implements IPersonService{
    @Autowired IPersonRepository ipersonRepository;
    
    @Override
    public List<Person> getPerson() {
        List<Person> person = ipersonRepository.findAll();
                return person;
    }

    @Override
    public void savePerson(Person person) {
        ipersonRepository.save(person);
                    }

    @Override
    public void deletePerson(Long id) {
        ipersonRepository.deleteById(id);
    }

    @Override
    public Person findPerson(Long id) {
        Person person = ipersonRepository.findById(id).orElse(null);
        return person;
    }
    
    public void edit(Person person){
        ipersonRepository.save(person);
    }  
    
}
