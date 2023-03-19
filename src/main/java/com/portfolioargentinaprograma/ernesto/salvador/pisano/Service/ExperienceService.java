/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolioargentinaprograma.ernesto.salvador.pisano.Service;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Entity.Experience;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Repository.IExperienceRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExperienceService {
    @Autowired
    IExperienceRepository iExperienceRepository;
    
    public List<Experience> list() {
        return iExperienceRepository.findAll();
    }    
    
    public Optional<Experience> getOne(int id){
        return iExperienceRepository.findById(id);
    }
    
    public Optional <Experience> getByCompanyName(String companyName){
        return iExperienceRepository.findByCompanyName(companyName);
    }
    
    public void save(Experience experience){
        iExperienceRepository.save(experience);
    }
    
    public void delete (int id){
        iExperienceRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return iExperienceRepository.existsById(id);
    }
    
    public boolean existsByCompanyName(String companyName){
        return iExperienceRepository.existsByCompanyName(companyName);
    }
    
}
