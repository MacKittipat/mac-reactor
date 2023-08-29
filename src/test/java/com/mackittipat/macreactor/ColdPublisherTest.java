package com.mackittipat.macreactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
* Cold Publisher.
* Publishers by default do not produce any value unless at least 1 observer subscribes to it.
* Publishers create new data producers for each new subscription.
*
* Reference :
* - https://www.vinsguru.com/reactor-hot-publisher-vs-cold-publisher/
*/
@Slf4j
public class ColdPublisherTest {

    @Test
    public void testColdProducer() throws InterruptedException {
        Flux coldProducer = Flux.just("1","2","3","4", "5").delayElements(Duration.ofSeconds(1));
        System.out.println("Starting ...");
        coldProducer.subscribe(n -> System.out.println("Cold 1 : " + n));
        Thread.sleep(2000);
        coldProducer.subscribe(n -> System.out.println("Cold 2 : " + n));
        Thread.sleep(10000);
    }

    // Convert hot to cold using defer.
}
