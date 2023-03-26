/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolioargentinaprograma.ernesto.salvador.pisano.Repository;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Entity.HSskills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HSskillsRepository extends JpaRepository<HSskills, Integer>{
    Optional<HSskills> findByName(String name);
    public boolean existsByName (String name);
}
