package com.microservice.chat.controller;


import com.microservice.chat.entity.Message;
import com.microservice.chat.entity.Room;
import com.microservice.chat.exceptions.ChatResponse;
import com.microservice.chat.repository.MessageRepository;
import com.microservice.chat.repository.RoomRepository;
import com.mysql.cj.protocol.MessageReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/messages") // done
    List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @GetMapping("/message/{id}") // done
    public ChatResponse getMessage(@PathVariable Long id) {
        try {
            Message findMessage = messageRepository.findById(id).get();
            return ChatResponse.ok().setPayload(findMessage);
        }catch (Exception e){
            return ChatResponse.notFound().setErrors(String.format("Message with id "+id+" was not found"));
        }
    }

    @PostMapping("/create")
    ChatResponse createMessage(@RequestBody Message message) {
        try {
            Message createMessage = messageRepository.save(message);
            return ChatResponse.ok().setPayload(createMessage);
        }catch(Exception e){
            return ChatResponse.badRequest().setErrors(String.format("Error creating message "+e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}") // done
    public ChatResponse deleteMessage(@PathVariable Long id) {
        try {
            messageRepository.deleteById(id);
            return ChatResponse.ok().setMetadata(String.format("Message deleted"));
        }catch(Exception e){
            return ChatResponse.notFound().setErrors(String.format("Error deleting message "+e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ChatResponse updateMessage(@RequestBody Message rmessage, @PathVariable Long id) {
        Message newMessage = messageRepository.findById(id).get();
        newMessage.setMessageContent(newMessage.getMessageContent());
        newMessage.setCreatedAt(newMessage.getCreatedAt());
        try{
            messageRepository.save(newMessage);;
            return ChatResponse.ok().setPayload(newMessage);
        }catch (Exception e){
            return ChatResponse.ok().setErrors(String.format("Message not updated: "+e.getMessage()));
        }
    }
}
