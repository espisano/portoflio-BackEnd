/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolioargentinaprograma.ernesto.salvador.pisano.controller;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Entity.HSskills;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.ExperienceDTO.HSskillsDTO;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Controller.RespMessage;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Service.HSskillsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/skills")
public class HSskillsController {

    @Autowired
    HSskillsService hsSkillsService;

    @GetMapping("/list")
    public ResponseEntity<List<HSskills>> list() {
        List<HSskills> list = hsSkillsService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<HSskills> getById(@PathVariable("id") int id) {
        if (!hsSkillsService.existsByID(id)) {
            return new ResponseEntity(new RespMessage("no existe"), HttpStatus.NOT_FOUND);
        }
        HSskills hsSkills = hsSkillsService.getOne(id).get();
        return new ResponseEntity(hsSkills, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody HSskillsDTO hsSkillsDTO) {
        if (StringUtils.isBlank(hsSkillsDTO.getName())) {
            return new ResponseEntity(new RespMessage("Ingrese una habilidad"), HttpStatus.BAD_REQUEST);
        }
        if (hsSkillsService.existsByName(hsSkillsDTO.getName())) {
            return new ResponseEntity(new RespMessage("El nombre de esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        }
        HSskills hsSkills = new HSskills(hsSkillsDTO.getName(), hsSkillsDTO.getPercentage());
        hsSkillsService.save(hsSkills);
        return new ResponseEntity(new RespMessage("Habilidad agregada correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody HSskillsDTO hsSkillsDTO) {

        if (!hsSkillsService.existsByID(id)) {
            return new ResponseEntity(new RespMessage("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (hsSkillsService.existsByName(hsSkillsDTO.getName()) && hsSkillsService.getByName(hsSkillsDTO.getName()).get().getId() != id) {
            return new ResponseEntity(new RespMessage("Esa Habilidad ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(hsSkillsDTO.getName())) {
            return new ResponseEntity(new RespMessage("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        HSskills hsSkills = hsSkillsService.getOne(id).get();
        hsSkills.setName(hsSkillsDTO.getName());
        hsSkills.setPercentage((hsSkillsDTO.getPercentage()));

        hsSkillsService.save(hsSkills);
        return new ResponseEntity(new RespMessage("Habilidad actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!hsSkillsService.existsByID(id)) {
            return new ResponseEntity(new RespMessage("El ID de esa habilidad no existe"), HttpStatus.NOT_FOUND);
        }
        hsSkillsService.delete(id);
        return new ResponseEntity(new RespMessage("Habilidad eliminada"), HttpStatus.OK);
    }

}
