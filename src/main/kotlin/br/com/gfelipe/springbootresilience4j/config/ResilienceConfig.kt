package br.com.gfelipe.springbootresilience4j.config

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreaker.State.*
import io.github.resilience4j.core.registry.EntryAddedEvent
import io.github.resilience4j.core.registry.EntryRemovedEvent
import io.github.resilience4j.core.registry.EntryReplacedEvent
import io.github.resilience4j.core.registry.RegistryEventConsumer
import org.slf4j.LoggerFactory
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


//@Configuration
//class ResilienceConfig(
//    private val messageListenerContainer: SimpleMessageListenerContainer
//) {
//
//    @Bean
//    fun myRegistryEventConsumer(): RegistryEventConsumer<CircuitBreaker> {
//        return object : RegistryEventConsumer<CircuitBreaker> {
//
//            val logger = LoggerFactory.getLogger(RegistryEventConsumer::class.java)
//
//            override fun onEntryAddedEvent(entryAddedEvent: EntryAddedEvent<CircuitBreaker>) {
//                logger.info("ENTRY ADDED EVENT: $entryAddedEvent")
//                entryAddedEvent.addedEntry.eventPublisher.onStateTransition { event ->
//                    when (event.stateTransition.toState) {
//                        OPEN, FORCED_OPEN ->  stopMessageListener()
//                        CLOSED, HALF_OPEN -> startMessageListener()
//                    }
//                }
//            }
//
//            private fun stopMessageListener() {
//                logger.info("CIRCUIT BREAKER ABERTO!")
//                if (messageListenerContainer.isRunning) {
//                    messageListenerContainer.stop()
//                }
//            }
//
//            private fun startMessageListener() {
//                logger.info("CIRCUIT BREAKER FECHADO!")
//                if (!messageListenerContainer.isRunning) {
//                    messageListenerContainer.start()
//                }
//            }
//
//            override fun onEntryRemovedEvent(entryRemoveEvent: EntryRemovedEvent<CircuitBreaker>) {
//            }
//
//            override fun onEntryReplacedEvent(entryReplacedEvent: EntryReplacedEvent<CircuitBreaker>) {
//            }
//        }
//
//    }
//
//}
