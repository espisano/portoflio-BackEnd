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
public class HSskillsDTO {
    @NotBlank
    private String name;
    @NotBlank
    private int percentage;

    public HSskillsDTO() {
    }

    public HSskillsDTO(String name, int percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
    
    
    
}
