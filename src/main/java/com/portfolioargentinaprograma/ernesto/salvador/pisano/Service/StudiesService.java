/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolioargentinaprograma.ernesto.salvador.pisano.Service;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Entity.Studies;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Repository.IStudiesRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudiesService {
    @Autowired
    IStudiesRepository iStudiesRepository;
    
    public List<Studies> list() {
        return iStudiesRepository.findAll();
    }    
    
    public Optional<Studies> getOne(int id){
        return iStudiesRepository.findById(id);
    }
    
    public Optional <Studies> getBySchoolName(String schoolName){
        return iStudiesRepository.findBySchoolName(schoolName);
    }
    
    public void save(Studies studies){
        iStudiesRepository.save(studies);
    }
    
    public void delete (int id){
        iStudiesRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return iStudiesRepository.existsById(id);
    }
    
    public boolean existsBySchoolName(String schoolName){
        return iStudiesRepository.existsBySchoolName(schoolName);
    }
    
}
