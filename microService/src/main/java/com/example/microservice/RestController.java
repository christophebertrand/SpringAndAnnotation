package com.example.microservice;


import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final IndexService indexService;
    private final UserRepository userRepository;

    public RestController(IndexService indexService, UserRepository userRepository) {
        this.indexService = indexService;
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "init", method = RequestMethod.POST)
    public ResponseEntity<?> init(@RequestBody List<User> users) {
        Iterable<User> it = userRepository.save(users);
        for (User u :
                it) {
            indexService.add(u);
        }
        return ResponseEntity.status(201).build();
    }


    @RequestMapping(value= "user", method = RequestMethod.GET)
    public List<User> getUser(@RequestParam(value = "lastName", defaultValue = "") String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @RequestMapping(value= "user/like", method = RequestMethod.GET)
    public CompletableFuture<List<User>> getUserLike(@RequestParam(value = "firstName", defaultValue = "") String lastName) {
        List<Long> ids = indexService.findLike(lastName);
        System.out.println(ids);
        return sequence(ids.stream().map(userRepository::findById).collect(toList()));
    }

    @Async
    CompletableFuture<List<User>> sequence(List<CompletableFuture<User>> com) {
        return CompletableFuture.allOf(com.toArray(new CompletableFuture[com.size()]))
                .thenApply(v -> com.stream()
                        .map(CompletableFuture::join)
                        .collect(toList())
                );
    }
}

