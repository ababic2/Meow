package com.microservice.chat.controller;


import com.microservice.chat.entity.Message;
import com.microservice.chat.entity.Room;
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
    public Message getMessage(@PathVariable Long id) {
        return messageRepository.findById(id).get();
    }

    @PostMapping("/create")
    Message createMessage(@RequestBody Message message) {
        return messageRepository.save(message);
    }

    @DeleteMapping("/delete/{id}") // done
    public void deleteMessage(@PathVariable Long id) {
        Optional<Message> message = messageRepository.findById(id);
        if(message.isPresent()) messageRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public Message updateMessage(@RequestBody Message rmessage, @PathVariable Long id) {
        Message newMessage = messageRepository.findById(id).get();
        newMessage.setMessageContent(newMessage.getMessageContent());
        newMessage.setCreatedAt(newMessage.getCreatedAt());
        messageRepository.save(newMessage);
        return newMessage;
    }
}
