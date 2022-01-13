package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Window;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * this interface presents the methods for accessing the heater data in the database
 */
public interface HeaterDao extends JpaRepository<Heater,Long>, HeaterDaoCustom {
    public Heater getById(Long id);
}
