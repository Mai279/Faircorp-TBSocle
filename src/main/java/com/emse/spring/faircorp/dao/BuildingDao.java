package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * this interface presents the data access methods for the buildings in the database
 */
public interface BuildingDao extends JpaRepository<Building, Long>, BuildingDaoCustom{
    public Building getById(Long id);
}
