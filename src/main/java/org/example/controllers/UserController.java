package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.models.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.example.services.UserService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Users> create(@RequestBody Users user) {
        return userService.createUser(user);
    }

    @GetMapping
    public Flux<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<Users>> getUserById(@PathVariable String userId){
        Mono<Users> user = userService.findById(userId);
        return user.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public Mono<ResponseEntity<Users>> updateUserById(@PathVariable String userId, @RequestBody Users user){
            return userService.updateUser(userId, user)
                    .map(ResponseEntity::ok)
                    .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{userId}")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable String userId) {
        return userService.deleteUser(userId)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public Flux<Users> searchUsers(@RequestParam("name") String name) {
        return userService.fetchUsers(name);
    }
}
