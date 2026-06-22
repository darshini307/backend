package com.example.backendFrontend;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://agent-6a38d5f95ac75d359c73--verdant-crisp-fc30b5.netlify.app/")
public class HelloController {

    private final MessageRepository repo;

    public HelloController(MessageRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/hello")
    public String sayHello() {
        // Return first message from DB, or default if empty
        List<Message> all = repo.findAll();
        if (all.isEmpty()) {
            repo.save(new Message("Hello from Spring Boot + DB!"));
            return "Hello from Spring Boot + DB!";
        }
        return all.get(1).getText();
    }
    @GetMapping("/getAll")
    public List<Message> getAll(){
        return repo.findAll();
    }

    @PostMapping("/hello")
    public String addMessage(@RequestBody Message msg) {
        repo.save(msg);
        return "Saved: " + msg.getText();
    }
}