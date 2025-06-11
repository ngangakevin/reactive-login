package org.example.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dtos.CreateUserDTO;
import org.example.dtos.UpdateUserDTO;
import org.example.models.Users;
import org.example.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.example.services.UserService;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<ApiResponse<Users>>> create(@RequestBody @Valid CreateUserDTO user) {
        Mono<Users> result = userService.createUser(user);
        return result.flatMap(ApiResponse::success);
    }

    @GetMapping
    public Mono<ResponseEntity<ApiResponse<List<Users>>>> getAllUsers() {
        return userService.getAllUsers()
                .collectList().flatMap((users -> {
                    return ApiResponse.of(true, "Request successful", users, HttpStatus.OK);
                }));
    }

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<ApiResponse<Users>>> getUserById(@PathVariable String userId){
        Mono<Users> user = userService.findById(userId);
        return user.flatMap(ApiResponse::success);
    }

    @PutMapping("/{userId}")
    public Mono<ResponseEntity<ApiResponse<Users>>> updateUserById(@PathVariable String userId, @RequestBody @Valid UpdateUserDTO user){
            return userService.updateUser(userId, user)
                    .flatMap(ApiResponse::success);
    }

    @DeleteMapping("/{userId}")
    public Mono<ResponseEntity<ApiResponse<Users>>> deleteUserById(@PathVariable String userId) {
        return userService.deleteUser(userId)
                .flatMap((user)->{
                    return ApiResponse.of(true, "User deletion successful", user, HttpStatus.OK);
                });
    }

    @GetMapping("/search")
    public Flux<Users> searchUsers(@RequestParam("name") String name) {
        return userService.fetchUsers(name);
    }
}
