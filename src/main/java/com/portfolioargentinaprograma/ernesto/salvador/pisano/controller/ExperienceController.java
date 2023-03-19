/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolioargentinaprograma.ernesto.salvador.pisano.controller;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Entity.Experience;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.ExperienceDTO.ExperienceDTO;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Controller.RespMessage;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Service.ExperienceService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jobexperience")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;

    @GetMapping("/list")
    public ResponseEntity<List<Experience>> list() {
        List<Experience> list = experienceService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ExperienceDTO experienceDTO) {
        if (StringUtils.isBlank(experienceDTO.getCompanyName())) {
            return new ResponseEntity(new RespMessage("Ingrese el nombre de la empresa"), HttpStatus.BAD_REQUEST);
        }
        if (experienceService.existsByCompanyName(experienceDTO.getCompanyName())) {
            return new ResponseEntity(new RespMessage("El nombre de esa empresa ya existe"), HttpStatus.BAD_REQUEST);
        }
        Experience experience = new Experience(experienceDTO.getCompanyName(), experienceDTO.getJobDescription());
        experienceService.save(experience);
        return new ResponseEntity(new RespMessage("Experiencia Laboral agregada correctamente"), HttpStatus.OK);
    }

      @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ExperienceDTO experienceDTO){

        if(!experienceService.existsById(id))
            return new ResponseEntity(new RespMessage("El ID no existe"), HttpStatus.BAD_REQUEST);

        if(experienceService.existsByCompanyName(experienceDTO.getCompanyName()) && experienceService.getByCompanyName(experienceDTO.getCompanyName()).get().getId() != id)
            return new ResponseEntity(new RespMessage("Esa experience ya existe"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(experienceDTO.getCompanyName()))
            return new ResponseEntity(new RespMessage("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Experience experience = experienceService.getOne(id).get();
        experience.setCompanyName(experienceDTO.getCompanyName());
        experience.setJobDescription((experienceDTO.getJobDescription()));
        
        experienceService.save(experience);
        return new ResponseEntity(new RespMessage("experience actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!experienceService.existsById(id)) {
            return new ResponseEntity(new RespMessage("El ID de esa empresa no existe"), HttpStatus.NOT_FOUND);
        }
        experienceService.delete(id);
        return new ResponseEntity(new RespMessage("Experiencia laboral eliminada"), HttpStatus.OK);
    }

}
