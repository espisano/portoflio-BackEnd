
package com.portfolioargentinaprograma.ernesto.salvador.pisano.controller;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Entity.Person;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Service.ImpPersonService;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("person")//localhost:8080/experience
@CrossOrigin(origins ="http://localhost:4200")
public class PersonController {
    @Autowired ImpPersonService IPersonService;
    
    @GetMapping ("/getList")
    public ResponseEntity<List<Person>> list(){
        List<Person> list = IPersonService.getPerson();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Person> detail(@PathVariable("id") Long id){
        Person person = IPersonService.findPerson(id);
        return new ResponseEntity(person, HttpStatus.OK);
    }       
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public void save(@RequestBody Person person){
        IPersonService.savePerson(person);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        IPersonService.deletePerson(id);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public void edit(@RequestBody Person person){
        IPersonService.edit(person);
    }
   
       
}