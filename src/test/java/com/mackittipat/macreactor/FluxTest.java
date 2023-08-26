package com.mackittipat.macreactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

public class FluxTest {

    @Test
    public void fluxJust() {
        Flux.just("Hello", "World", "Good").subscribe(System.out::println);
    }

    @Test
    public void fluxEmpty() {
        Flux.empty().subscribe(System.out::println);
    }

    @Test
    public void fluxAsList() {
        Flux.fromIterable(Arrays.asList("1", "2", "3")).subscribe(System.out::println);
    }

    @Test
    public void fluxFrom() {
        Flux.from(Flux.just("Hello", "World", "Good")).subscribe(System.out::println);
    }


    @Test
    public void fluxFromArray() {
        Flux.fromArray(new String[]{"A", "B", "C"}).subscribe(System.out::println);
    }

    @Test
    public void fluxFromStream() {
        Flux.fromStream(Arrays.asList("1", "2", "3").stream()).subscribe(System.out::println);
    }

    @Test
    public void fluxRange() {
        Flux.range(1, 3).subscribe(System.out::println);
    }

    @Test
    public void fluxInterval() throws InterruptedException {
        Flux.interval(Duration.ofMillis(300)).take(3).subscribe(System.out::println);
        Thread.sleep(1000);
    }

    @Test
    public void fluxCreateSuccess() {
        Flux.create(emitter -> {
            for (int i = 0; i < 10; i++) {
                emitter.next("next : " + i);
            }
            emitter.complete();
        }).subscribe(
                System.out::println,
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("Flux consumed.")
        );
    }

    @Test
    public void fluxCreateError() {
        Flux.create(emitter -> {
            for (int i = 0; i < 10; i++) {
                if (i == 5) {
                    emitter.error(new IllegalArgumentException("There is an error!"));
                    return;
                }
                emitter.next("next : " + i);
            }
            emitter.complete();
        }).subscribe(
                System.out::println,
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("Flux consumed.")
        );
    }

}
