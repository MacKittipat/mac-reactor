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
        Flux.fromArray(new String[] {"A", "B", "C"}).subscribe(System.out::println);
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

}
