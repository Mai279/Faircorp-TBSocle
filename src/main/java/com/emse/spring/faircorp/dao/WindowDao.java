package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * this interface presents the methods for accessing data relating to the windows in the database
 */
public interface WindowDao extends JpaRepository<Window,Long>, WindowDaoCustom{
    public Window getById(Long id);
}
