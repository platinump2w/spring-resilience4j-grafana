package br.com.gfelipe.springbootresilience4j

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean


@SpringBootApplication
@EnableFeignClients
class SpringBootResilience4jApplication

fun main(args: Array<String>) {
	runApplication<SpringBootResilience4jApplication>(*args)
}
//
//@Bean
//fun testCustomizer(): CircuitBreakerConfigCustomizer =
//	CircuitBreakerConfigCustomizer
//		.of("backendA") {
//				builder: CircuitBreakerConfig.Builder -> builder.slidingWindowSize(
//				100
//			)
//		}
