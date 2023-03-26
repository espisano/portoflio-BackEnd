/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolioargentinaprograma.ernesto.salvador.pisano.Service;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Entity.HSskills;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Repository.HSskillsRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service

public class HSskillsService {

    @Autowired
    HSskillsRepository hsSkillsRepository;

    public List<HSskills> list() {
        return hsSkillsRepository.findAll();

    }

    public Optional<HSskills> getOne(int id) {
        return hsSkillsRepository.findById(id);

    }

    public Optional<HSskills> getByName(String name) {
        return hsSkillsRepository.findByName(name);
    }

    public void save(HSskills skills) {
        hsSkillsRepository.save(skills);
    }

    public void delete(int id) {
        hsSkillsRepository.deleteById(id);
    }

    public boolean existsByID(int id) {
        return hsSkillsRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return hsSkillsRepository.existsByName(name);
    }
}
