package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  this class is a REST Controller for the Room class of the application
 */
@CrossOrigin
@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {

    private final RoomDao roomDao;
    private final BuildingDao buildingDao;

    private final WindowDao windowDao;
    private final HeaterDao heaterDao;

    /**
     * Constructor
     */
    public RoomController(RoomDao roomDao, BuildingDao buildingDao, WindowDao windowDao, HeaterDao heaterDao){
        this.roomDao = roomDao;
        this.buildingDao = buildingDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
    }

    /**
     * Retrieve the room list via a GET
     * @return  list of all rooms (in DTO format)
     */
    @GetMapping
    public List<RoomDto> findAll(){
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    /**
     * Retrieve a particular room via a GET
     * @param room_id    id of the room we want to see
     * @return      the desired room in DTO format
     */
    @GetMapping(path = "/{room_id}")
    public RoomDto findById(@PathVariable Long room_id){
        return roomDao.findById(room_id).map(RoomDto::new).orElse(null);
    }

    /**
     * Create or update a room via a POST
     * @param dto   the room requested by the customer
     * @return      this room created or updated
     */
    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto){
        //RoomDto must always contain room's building information
        Building building = buildingDao.getById(dto.getBuildingId());
        Room room = null;
        // create if request with dto id == null
        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getFloor(), dto.getName(), building));
        }
        else{
            room = roomDao.getById(dto.getId());
            room.setFloor(dto.getFloor());
            room.setName(dto.getName());
            room.setCurrentTemperature(dto.getCurrentTemperature());
            room.setTargetTemperature(dto.getTargetTemperature());
        }
        return new RoomDto(room);
    }

    /**
     * Update all windows in the room and switch their status via a PUT
     * @param room_id    id of the room where we want to switch windows' status
     * @return      the update room in DTO format
     */
    @PutMapping(path = "/{room_id}/switchWindow")
    public RoomDto switchStatusWindows(@PathVariable Long room_id) {
        Long id = room_id;
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        room.getWindows().forEach(window -> window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED : WindowStatus.OPEN)
        );
        return new RoomDto(room);
    }

    /**
     * Update all heaters in the room and switch their status via a PUT
     * @param room_id    id of the room where we want to switch heaters' status
     * @return      the update room in DTO format
     */
    @PutMapping(path = "/{room_id}/switchHeaters")
    public RoomDto switchStatusHeaters(@PathVariable Long room_id) {
        Long id = room_id;
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        room.getHeaters().forEach(heater->
                heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON)
        );
        return new RoomDto(room);
    }

    /**
     * Delete a room and all its windows and heaters via a DELETE
     * @param room_id    id of the room we want to delete
     */
    @DeleteMapping(path = "/{room_id}")
    public void delete(@PathVariable Long room_id){
        windowDao.deleteWindowsRoom(room_id);
        heaterDao.deleteHeatersRoom(room_id);
        roomDao.deleteById(room_id);
    }
}
