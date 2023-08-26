package com.mackittipat.macreactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoTest {

    @Test
    public void monoJust() {
        Mono.just("Hello").subscribe(System.out::println);
    }

    @Test
    public void monoEmpty() {
        Mono.empty().subscribe(System.out::println);
    }

    @Test
    public void monoFrom() {
        Mono.from(Flux.just("Flux 1", "Flux 2", "Flux 3")).subscribe(System.out::println);
        Mono.from(Mono.just("Mono 1")).subscribe(System.out::println);
    }

    @Test
    public void monoCreateSuccess() {
        Mono.create(callback -> {
            try {
                callback.success("Hello");
            } catch (Exception e) {
                callback.error(e);
            }
        }).subscribe(
                System.out::println,
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("Mono consumed.")
        );
    }

    @Test
    public void monoCreateError() {
        Mono.create(callback -> {
            try {
                callback.error(new IllegalArgumentException("There is an error!"));
            } catch (Exception e) {
                callback.error(e);
            }
        }).subscribe(
                System.out::println,
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("Mono consumed.")
        );
    }
}
