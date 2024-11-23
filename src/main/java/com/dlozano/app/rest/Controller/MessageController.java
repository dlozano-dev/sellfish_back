package com.dlozano.app.rest.Controller;

import com.dlozano.app.rest.Models.Message;
import com.dlozano.app.rest.Repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping(value = "/message/user/{user}/product/{product}")
    public List<Message> message(@PathVariable int user, @PathVariable int product) {
        return messageRepo.getMessages(user, product);
    }

    @PostMapping(value = "/postMessage")
    public boolean postMessage(@RequestBody Message message) {
        try {
            messageRepo.save(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
