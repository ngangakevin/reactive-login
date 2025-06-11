package org.example.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;

    public static <T> Mono<ResponseEntity<ApiResponse<T>>> success(T data){
        return Mono.just(ResponseEntity.ok(ApiResponse.<T>builder()
                .success(true)
                .message("Request successful")
                .data(data)
                .build()));
    }

    public static <T> Mono<ResponseEntity<ApiResponse<T>>> error(String message, HttpStatus status){
        return Mono.just(ResponseEntity.status(status).body(ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .build()));
    }

    public static <T> Mono<ResponseEntity<ApiResponse<T>>> of(boolean success, String message, T data, HttpStatus status){
        return Mono.just(ResponseEntity.status(status).body(new ApiResponse<>(success, message, data)));
    }
}
