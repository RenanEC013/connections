package com.pingr.Connections.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/friendships")
public class FriendshipController {
    private final FriendshipService service;

    @Autowired
    public FriendshipController(FriendshipService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Friendship createOneFriendship(@RequestBody Account account1, @RequestBody Account account2) {
        System.out.println(account1);
        System.out.println(account2);

        return this.service.createFriendship(account1, account2);
    }

    @DeleteMapping("/friendships/{id},{acc1},{acc2}")
    public void deleteFriendship(@PathVariable Long id, @PathVariable Account acc1,
                                 @PathVariable Account acc2) {

        this.service.deleteFriendship(id, acc1, acc2);
    }

    @GetMapping("/friendships/{id}")
    public Integer getNumberFriends(@PathVariable Account acc) {

        return this.service.getNumberFriends(acc);
    }

}
