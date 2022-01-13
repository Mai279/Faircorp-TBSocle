package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.*;
import com.emse.spring.faircorp.dto.BuildingDto;
import com.emse.spring.faircorp.model.Building;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  this class is a REST Controller for the Building class of the application
 */
@CrossOrigin
@RestController
@RequestMapping("/api/buildings")
@Transactional
public class BuildingController {

    private final BuildingDao buildingDao;

    private final RoomDao roomDao;
    private final WindowDao windowDao;
    private final HeaterDao heaterDao;

    /**
     * Constructor
     */
    public BuildingController(BuildingDao buildingDao, RoomDao roomDao, HeaterDao heaterDao, WindowDao windowDao){
        this.roomDao = roomDao;
        this.buildingDao = buildingDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
    }

    /**
     * Retrieve the building list via a GET
     * @return  list of all buildings (in DTO format)
     */
    @GetMapping
    public List<BuildingDto> findAll(){
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    /**
     * Retrieve a particular building via a GET
     * @param building_id    id of the building we want to see
     * @return               the desired building in DTO format
     */
    @GetMapping(path = "/{building_id}")
    public BuildingDto findById(@PathVariable Long building_id){
        return buildingDao.findById(building_id).map(BuildingDto::new).orElse(null);
    }

    /**
     * Create or update a building via a POST
     * @param dto   the building requested by the customer
     * @return      this building created or updated
     */
    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto dto){
        Building building = null;
        if (dto.getId() == null) {
            building = buildingDao.save(new Building(dto.getName()));
        }
        else{
            building = buildingDao.getById(dto.getId());
            building.setName(dto.getName());
        }
        return new BuildingDto(building);
    }

    /**
     * Delete a building and all its rooms and windows and heaters via a DELETE
     * @param building_id    id of the room we want to delete
     */
    @DeleteMapping(path = "/{building_id}")
    public void delete(@PathVariable Long building_id){
        List<Long> idRooms = buildingDao.findIdRoomsInBuilding(building_id);
        for (Long room_id : idRooms){
            windowDao.deleteWindowsRoom(room_id);
            heaterDao.deleteHeatersRoom(room_id);
            roomDao.deleteById(room_id);
        }
        buildingDao.deleteById(building_id);
    }
}
