/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolioargentinaprograma.ernesto.salvador.pisano.controller;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Entity.Studies;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.ExperienceDTO.StudiesDTO;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Controller.RespMessage;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Service.StudiesService;
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
@RequestMapping("studies")
@CrossOrigin(origins = "http://localhost:4200")
public class StudiesController {

    @Autowired
    StudiesService studiesService;

    @GetMapping("/list")
    public ResponseEntity<List<Studies>> list() {
        List<Studies> list = studiesService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Studies> getById(@PathVariable("id") int id){
        if(!studiesService.existsById(id))
            return new ResponseEntity(new RespMessage("no existe"), HttpStatus.NOT_FOUND);
        Studies studies = studiesService.getOne(id).get();
        return new ResponseEntity(studies, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudiesDTO studiesDTO) {
        if (StringUtils.isBlank(studiesDTO.getSchoolName())) {
            return new ResponseEntity(new RespMessage("Ingrese el nombre de la institucion"), HttpStatus.BAD_REQUEST);
        }
        if (studiesService.existsBySchoolName(studiesDTO.getSchoolName())) {
            return new ResponseEntity(new RespMessage("El nombre de esa institucion ya existe"), HttpStatus.BAD_REQUEST);
        }
        Studies studies = new Studies(studiesDTO.getSchoolName(), studiesDTO.getTitle());
        studiesService.save(studies);
        return new ResponseEntity(new RespMessage("Carrera agregada correctamente"), HttpStatus.OK);
    }

      @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody StudiesDTO studiesDTO){

        if(!studiesService.existsById(id))
            return new ResponseEntity(new RespMessage("El ID no existe"), HttpStatus.BAD_REQUEST);

        if(studiesService.existsBySchoolName(studiesDTO.getSchoolName()) && studiesService.getBySchoolName(studiesDTO.getSchoolName()).get().getId() != id)
            return new ResponseEntity(new RespMessage("Esa carrera ya existe"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(studiesDTO.getSchoolName()))
            return new ResponseEntity(new RespMessage("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Studies studies = studiesService.getOne(id).get();
        studies.setSchoolName(studiesDTO.getSchoolName());
        studies.setTitle((studiesDTO.getTitle()));
        
        studiesService.save(studies);
        return new ResponseEntity(new RespMessage("Carrera actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!studiesService.existsById(id)) {
            return new ResponseEntity(new RespMessage("El ID de esa empresa no existe"), HttpStatus.NOT_FOUND);
        }
        studiesService.delete(id);
        return new ResponseEntity(new RespMessage("Carrera eliminada"), HttpStatus.OK);
    }

}
