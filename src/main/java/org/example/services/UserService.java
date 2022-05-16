package org.example.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.models.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.example.repositories.UserRepository;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public Mono<Users> createUser(Users user) {
        return userRepository.save(user);
    }

    public Flux<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<Users> findById(String userId) {
        return userRepository.findById(userId);
    }

    public Mono<Users> updateUser(String userId, Users user) {
        return userRepository.findById(userId)
                .flatMap(dbUser -> {
                    dbUser.setAge(user.getAge());
                    dbUser.setSalary(user.getSalary());
                    return userRepository.save(dbUser);
                });
    }

    public Mono<Users> deleteUser(String userId) {
        return userRepository.findById(userId)
                .flatMap(existingUser -> userRepository.delete(existingUser)
                        .then(Mono.just(existingUser))
                );
    }

    public Flux<Users> fetchUsers(String name) {
        return userRepository.findByName(name);
    }
}
