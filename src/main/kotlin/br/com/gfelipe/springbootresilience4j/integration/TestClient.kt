package br.com.gfelipe.springbootresilience4j.integration

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("backendA", url = "\${integration.test.url}")
interface TestClient {

    @Retry(name = "backendA")
    @CircuitBreaker(name = "backendA")
    @GetMapping("/some/thing/{status}")
    fun test(@PathVariable status: String): String

    @CircuitBreaker(name = "backendB")
    @GetMapping("/some/thingb")
    fun testb(): String

}
