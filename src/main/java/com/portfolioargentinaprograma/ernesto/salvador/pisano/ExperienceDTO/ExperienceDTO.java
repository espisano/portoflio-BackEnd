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
public class ExperienceDTO {
    @NotBlank
    private String companyName;
    @NotBlank
    private String jobDescription;

    public ExperienceDTO() {
    }

    public ExperienceDTO(String companyName, String jobDescription) {
        this.companyName = companyName;
        this.jobDescription = jobDescription;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
    
    
}
