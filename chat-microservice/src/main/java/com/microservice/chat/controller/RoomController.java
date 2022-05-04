package com.microservice.chat.controller;

import com.microservice.chat.entity.Message;
import com.microservice.chat.entity.Room;
import com.microservice.chat.entity.User;
import com.microservice.chat.exceptions.ChatResponse;
import com.microservice.chat.repository.RoomRepository;
import com.microservice.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/chat")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/rooms") // done
    List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/room/{id}") // done
    public ChatResponse getRoom(@PathVariable Long id) {
        try {
            Room findRoom = roomRepository.findById(id).get();
            return ChatResponse.ok().setPayload(findRoom);
        }catch (Exception e){
            return ChatResponse.notFound().addErrorMsgToResponse("Room with id "+id+" was not found", e);
        }
    }

    @PostMapping("/room")
    ChatResponse createRoom(@Valid @RequestBody Room room) {
        try {
            Room createRoom = roomRepository.save(room);
            return ChatResponse.ok().setPayload(createRoom);
        }catch(Exception e){
            return ChatResponse.badRequest().addErrorMsgToResponse("Error creating room ",e);
        }
    }

    @DeleteMapping("/room/{id}") // done
    public ChatResponse deleteRoom(@PathVariable Long id) {
        try {
            roomRepository.deleteById(id);
            return ChatResponse.ok().setMetadata(String.format("Room deleted"));
        }catch(Exception e){
            return ChatResponse.notFound().addErrorMsgToResponse("Error deleting room ",e);
        }
    }

    @PutMapping("/room/{id}")
    public ChatResponse updateRoom(@Valid @RequestBody Room room, @PathVariable Long id) {
        Room newRoom = roomRepository.findById(id).get();
        newRoom.setName(room.getName());

        try{
            roomRepository.save(newRoom);
            return ChatResponse.ok().setPayload(newRoom);
        }catch (Exception e){
            return ChatResponse.ok().addErrorMsgToResponse("Room not updated: ",e);
        }
    }
}
