package com.dlozano.app.rest.Controller;

import com.dlozano.app.rest.Models.Clothes;
import com.dlozano.app.rest.Models.Message;
import com.dlozano.app.rest.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(value = "/message/user/{user}/product/{product}")
    public List<Message> message(@PathVariable int user, @PathVariable int product) {
        return messageRepository.getMessages(user, product);
    }

    @GetMapping(value = "/postMessage/{user}/{publisher}/{product}/{message}/{time}/{buyer}")
    public boolean postMessage(
        @PathVariable int user,
        @PathVariable int publisher,
        @PathVariable int product,
        @PathVariable String message,
        @PathVariable long time,
        @PathVariable int buyer
    ) {
        try {
            messageRepository.save(
                new Message(
                    user,
                    publisher,
                    product,
                    message,
                    time,
                    buyer
                )
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping(value = "chats/{user}")
    public List<Clothes> getChats(@PathVariable int user) {
        return messageRepository.getChats(user);
    }
}
