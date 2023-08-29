package com.mackittipat.macreactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
* Hot Publisher.
* Hot Publishers do not create new data producer for each new subscription (as the Cold Publisher does).
* Instead there will be only one data producer and all the observers listen to the data produced by the single data producer.
* So all the observers get the same data.
*
* Reference :
* - https://www.vinsguru.com/reactor-hot-publisher-vs-cold-publisher/
*/
public class HotPublisherTest {

    @Test
    public void testHotPublisher() throws InterruptedException {
        Flux coldProducer = Flux.just("1","2","3","4", "5").delayElements(Duration.ofSeconds(1));
        ConnectableFlux<String> hotProducer = coldProducer.publish();
        hotProducer.connect();
        System.out.println("Starting ...");
        hotProducer.subscribe(n -> System.out.println("Hot 1 : " + n));
        Thread.sleep(2000);
        hotProducer.subscribe(n -> System.out.println("Hot 2 : " + n));
        Thread.sleep(10000);
    }

    // Convert cold to hot using share and cache
}
