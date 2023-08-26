package com.mackittipat.macreactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

// https://jstobigdata.com/java/transform-and-combine-reactive-stream/
// https://medium.com/digitalfrontiers/into-the-jungle-of-reactive-operators-c280c3e310d7
public class OperatorTest {

    // map is for synchronous, non-blocking, 1-to-1 transformations
    @Test
    public void testMap() {
        Flux<Integer> result = Flux.just(1, 2, 3).map(i -> i * 2);
        StepVerifier.create(result)
                .expectNext(2)
                .expectNext(4)
                .expectNext(6)
                .expectComplete()
                .verify();
    }

    // flatMap is for asynchronous (non-blocking) 1-to-N transformations
    @Test
    public void testFlatMap() {
        Flux<Integer> result = Flux.just(1, 2, 3).flatMap(i -> Flux.range(0, i));
        StepVerifier.create(result)
                .expectNext(0)
                .expectNext(0)
                .expectNext(1)
                .expectNext(0)
                .expectNext(1)
                .expectNext(2)
                .expectComplete()
                .verify();
    }

    @Test
    public void testZip() {
        Flux<String> result = Flux.zip(
                Flux.just(1, 2, 3),
                Flux.just("A", "B", "C"),
                (a, b) -> a + b);
        StepVerifier.create(result)
                .expectNext("1A")
                .expectNext("2B")
                .expectNext("3C")
                .expectComplete()
                .verify();
    }

    @Test
    public void testMerge() {
        Flux<String> result = Flux.merge(
                Flux.just("1", "2", "3").delayElements(Duration.ofMillis(100)),
                Flux.just("A", "B", "C").delayElements(Duration.ofMillis(250)));
        StepVerifier.create(result)
                .expectNext("1")
                .expectNext("2")
                .expectNext("A")
                .expectNext("3")
                .expectNext("B")
                .expectNext("C")
                .expectComplete()
                .verify();
    }

    @Test
    public void testConcat() {
        Flux<String> result = Flux.concat(
                Flux.just("1", "2", "3").delayElements(Duration.ofMillis(100)),
                Flux.just("A", "B", "C").delayElements(Duration.ofMillis(250)));
        StepVerifier.create(result)
                .expectNext("1")
                .expectNext("2")
                .expectNext("3")
                .expectNext("A")
                .expectNext("B")
                .expectNext("C")
                .expectComplete()
                .verify();
    }

    @Test
    public void testThenMany() {
        Flux<String> result = Flux.just("1", "2", "3")
                .thenMany(Flux.just("A", "B", "C"));
        StepVerifier.create(result)
                .expectNext("A")
                .expectNext("B")
                .expectNext("C")
                .expectComplete()
                .verify();
    }

    @Test
    public void testDefer() {
        // All subscriber get same data
        // Math.random() is executed immediately
        Flux<String> hotFlux = Flux.just(Math.random() + "");
        hotFlux.subscribe(System.out::println);
        hotFlux.subscribe(System.out::println);

        // All subscriber get difference data.
        // The defer operator is there to make this source lazy,
        // Math.random() is executed each time there is a new subscriber.
        Flux<String> coldFlux = Flux.defer(() -> Flux.just(Math.random() + ""));
        coldFlux.subscribe(System.out::println);
        coldFlux.subscribe(System.out::println);
    }
}
