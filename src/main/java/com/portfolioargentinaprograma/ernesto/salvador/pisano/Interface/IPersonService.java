
package com.portfolioargentinaprograma.ernesto.salvador.pisano.Interface;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Entity.Person;
import java.util.List;


public interface IPersonService {
    public List<Person> getPerson();
    public void savePerson(Person person);
    public void deletePerson(Long id);
    public Person findPerson(Long id);
    
}
