package com.microservice.chat.controller;

import com.microservice.chat.entity.Room;
import com.microservice.chat.entity.User;
import com.microservice.chat.repository.RoomRepository;
import com.microservice.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/rooms") // done
    List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/room/{id}") // done
    public Room getRoom(@PathVariable Long id) {
        return roomRepository.findById(id).get();
    }

    @PostMapping("/create")
    Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @DeleteMapping("/delete/{id}") // done
    public void deleteRoom(@PathVariable Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if(room.isPresent()) roomRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public Room updateRoom(@RequestBody Room room, @PathVariable Long id) {
        Room newRoom = roomRepository.findById(id).get();
        newRoom.setName(room.getName());
        roomRepository.save(newRoom);
        return newRoom;
    }
}
