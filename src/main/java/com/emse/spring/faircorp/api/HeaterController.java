package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.model.*;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  this class is a REST Controller for the heater class of the application
 */
@CrossOrigin
@RestController
@RequestMapping("/api/heaters")
@Transactional
public class HeaterController {

    private final HeaterDao heaterDao;
    private final RoomDao roomDao;

    /**
     * Constructor
     */
    public HeaterController(HeaterDao heaterDao, RoomDao roomDao) {
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
    }

    /**
     * Retrieve the heater list via a GET
     * @return  list of all heaters (in DTO format)
     */
    @GetMapping
    public List<HeaterDto> findAll() {
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());
    }

    /**
     * Retrieve a particular heater via a GET
     * @param heater_id    id of the heater we want to see
     * @return      the desired heater in DTO format
     */
    @GetMapping(path = "/{heater_id}")
    public HeaterDto findById(@PathVariable Long heater_id) {
        return heaterDao.findById(heater_id).map(HeaterDto::new).orElse(null);
    }

    /**
     * Update a heater and switch its status via a PUT
     * @param heater_id    id of the heater
     * @return      the update heater in DTO format
     */
    @PutMapping(path = "/{heater_id}/switch")
    public HeaterDto switchStatus(@PathVariable Long heater_id) {
        Heater heater = heaterDao.findById(heater_id).orElseThrow(IllegalArgumentException::new);
        heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON);
        return new HeaterDto(heater);
    }
    /**
     * Create or update a heater via a POST
     * @param dto   the heater requested by the customer
     * @return      this heater created or updated
     */
    @PostMapping
    public HeaterDto create(@RequestBody HeaterDto dto) {
        Room room = roomDao.getById(dto.getRoomId());
        Heater heater = null;
        // On creation id is not defined
        // create if heater does not exist
        if (dto.getId() == null) {
            heater = heaterDao.save(new Heater(dto.getHeaterStatus(), dto.getName(), dto.getPower(), room));
        }
        // update if already exist
        else {
            heater = heaterDao.getById(dto.getId());
            heater.setHeaterStatus(dto.getHeaterStatus());
            heater.setName(dto.getName());
            heater.setPower(dto.getPower());
        }
        return new HeaterDto(heater);
    }

    /**
     * Delete a window via a DELETE
     * @param heater_id    id of the window we want to delete
     */
    @DeleteMapping(path = "/{heater_id}")
    public void delete(@PathVariable Long heater_id) {
        heaterDao.deleteById(heater_id);
    }
}