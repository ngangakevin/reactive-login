//package handlers;
//
//import lombok.RequiredArgsConstructor;
//import models.Users;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import reactor.core.publisher.Mono;
//import services.UserService;
//
//@Component
//@RequiredArgsConstructor
//public class UserHandler {
//
//    private final UserService userService;
//
//    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
//        return ServerResponse
//                .ok()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(userService.getAllUsers(), Users.class);
//    }
//
//    public Mono<ServerResponse> getUserById(ServerRequest request) {
//        return userService
//                .findById(request.pathVariable("userId"))
//                .flatMap(user -> ServerResponse
//                        .ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(user, Users.class)
//                )
//                .switchIfEmpty(ServerResponse.notFound().build());
//    }
//
//    public Mono<ServerResponse> create(ServerRequest request) {
//        Mono<Users> user = request.bodyToMono(Users.class);
//
//        return user
//                .flatMap(u -> ServerResponse
//                        .status(HttpStatus.CREATED)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(userService.createUser(u), Users.class)
//                );
//    }
//
//    public Mono<ServerResponse> updateUserById(ServerRequest request) {
//        String id = request.pathVariable("userId");
//        Mono<Users> updatedUser = request.bodyToMono(Users.class);
//
//        return updatedUser
//                .flatMap(u -> ServerResponse
//                        .ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(userService.updateUser(id, u), Users.class)
//                );
//    }
//
//    public Mono<ServerResponse> deleteUserById(ServerRequest request){
//        return userService.deleteUser(request.pathVariable("userId"))
//                .flatMap(u -> ServerResponse.ok().body(u, Users.class))
//                .switchIfEmpty(ServerResponse.notFound().build());
//    }
//}