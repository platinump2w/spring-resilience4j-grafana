package br.com.gfelipe.springbootresilience4j.config

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import org.slf4j.LoggerFactory
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

import io.github.resilience4j.circuitbreaker.CircuitBreaker.State.*
import org.slf4j.Logger
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer

@Component
class StartupApplicationListener(
    private val circuitBreakerRegistry: CircuitBreakerRegistry,
    private val messageListenerContainer: SimpleMessageListenerContainer
) {

    val logger: Logger = LoggerFactory.getLogger(StartupApplicationListener::class.java)

    @EventListener
    fun onApplicationEvent(event: ContextRefreshedEvent) {
        logger.info("Application Event: Context Refreshed")
        configureCircuitBreaker()
    }

    private fun configureCircuitBreaker() {
        logger.info("Configuring CircuitBreakers")

        messageListenerContainer.queueStopTimeout = 60000

        circuitBreakerRegistry.allCircuitBreakers.forEach { it ->
            it.eventPublisher.onStateTransition {
                when (it.stateTransition.toState) {
                    OPEN, FORCED_OPEN -> stopMessageListener()
                    CLOSED, HALF_OPEN -> startMessageListener()
                }
            }
        }
    }

    private fun stopMessageListener() {
        logger.info("CIRCUIT BREAKER ABERTO! Parando o consumo de mensagens do listener")
        if (messageListenerContainer.isRunning) {
            messageListenerContainer.stop("test-sqs-queue")
        }
    }

    private fun startMessageListener() {
        logger.info("CIRCUIT BREAKER FECHADO! Retomando o consumo de mensagens do listener")
        if (!messageListenerContainer.isRunning) {
            messageListenerContainer.start("test-sqs-queue")
        }
    }

}
