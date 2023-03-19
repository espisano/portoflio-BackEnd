/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolioargentinaprograma.ernesto.salvador.pisano.Repository;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Entity.Experience;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExperienceRepository extends JpaRepository<Experience, Integer>{
    public Optional<Experience> findByCompanyName(String companyName);
    public boolean existsByCompanyName(String companyName);
}
