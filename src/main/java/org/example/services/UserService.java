package org.example.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dtos.CreateUserDTO;
import org.example.dtos.UpdateUserDTO;
import org.example.models.Users;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.example.repositories.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final UserRepository userRepository;

    public Mono<Users> createUser(CreateUserDTO user) {
        Users dbUser = Users.fromDTO(user);
        return userRepository.save(dbUser);
    }

    public Flux<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<Users> findById(String userId) {
        return userRepository.findById(userId);
    }

    public Mono<Users> updateUser(String userId, UpdateUserDTO user) {
        return userRepository.findById(userId)
                .flatMap(dbUser -> {
                    if(user.getSalary() != null) dbUser.setSalary(user.getSalary());
                    if(user.getAge() != null) dbUser.setAge(user.getAge());
//                    Optional.ofNullable(user.getSalary()).ifPresent(dbUser::setSalary);
//                    Optional.ofNullable(user.getAge()).ifPresent(dbUser::setAge);
                    return userRepository.save(dbUser);
                });
    }

    public Mono<Users> deleteUser(String userId) {
        return userRepository.findById(userId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not not found")))
                .flatMap(existingUser -> userRepository.delete(existingUser)
                        .then(Mono.just(existingUser))
                );
    }

    public Flux<Users> fetchUsers(String name) {
        Query query = new Query()
                .with(Sort.by(Collections.singletonList(Sort.Order.asc("age")))
                );
        query.addCriteria(Criteria
                .where("name")
                .regex(name)
        );
        return reactiveMongoTemplate.find(query, Users.class);
    }
}
