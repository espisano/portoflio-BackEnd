/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolioargentinaprograma.ernesto.salvador.pisano.ExperienceDTO;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Dell
 */
public class StudiesDTO {   
    @NotBlank
    private String schoolName;
    @NotBlank
    private String title;

    public StudiesDTO() {
    }

    public StudiesDTO(String schoolName, String title) {
        this.schoolName = schoolName;
        this.title = title;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolyName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
