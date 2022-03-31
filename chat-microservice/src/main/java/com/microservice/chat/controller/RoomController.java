package com.microservice.chat.controller;

import com.microservice.chat.entity.Message;
import com.microservice.chat.entity.Room;
import com.microservice.chat.entity.User;
import com.microservice.chat.exceptions.ChatResponse;
import com.microservice.chat.repository.RoomRepository;
import com.microservice.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/chat")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/room") // done
    List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/room/{id}") // done
    public ChatResponse getRoom(@PathVariable Long id) {
        try {
            Room findRoom = roomRepository.findById(id).get();
            return ChatResponse.ok().setPayload(findRoom);
        }catch (Exception e){
            return ChatResponse.notFound().setErrors(String.format("Room with id "+id+" was not found"));
        }
    }

    @PostMapping("/room")
    ChatResponse createRoom(@RequestBody Room room) {
        try {
            Room createRoom = roomRepository.save(room);
            return ChatResponse.ok().setPayload(createRoom);
        }catch(Exception e){
            return ChatResponse.badRequest().setErrors(String.format("Error creating room "+e.getMessage()));
        }
    }

    @DeleteMapping("/room/{id}") // done
    public ChatResponse deleteRoom(@PathVariable Long id) {
        try {
            roomRepository.deleteById(id);
            return ChatResponse.ok().setMetadata(String.format("Room deleted"));
        }catch(Exception e){
            return ChatResponse.notFound().setErrors(String.format("Error deleting room "+e.getMessage()));
        }
    }

    @PutMapping("/room/{id}")
    public ChatResponse updateRoom(@RequestBody Room room, @PathVariable Long id) {
        Room newRoom = roomRepository.findById(id).get();
        newRoom.setName(room.getName());

        try{
            roomRepository.save(newRoom);
            return ChatResponse.ok().setPayload(newRoom);
        }catch (Exception e){
            return ChatResponse.ok().setErrors(String.format("Room not updated: "+e.getMessage()));
        }
    }
}
