package br.com.gfelipe.springbootresilience4j.listener

import br.com.gfelipe.springbootresilience4j.service.TestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val testService: TestService
) {

    @GetMapping
    fun test() = testService.credit()

}
