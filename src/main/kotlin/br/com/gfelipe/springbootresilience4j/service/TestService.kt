package br.com.gfelipe.springbootresilience4j.service

import br.com.gfelipe.springbootresilience4j.integration.TestClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.concurrent.Executors
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

@Service
class TestService(
    private val testClient: TestClient
) {

    val logger = LoggerFactory.getLogger(TestService::class.java)

    val a = ThreadPoolExecutor(
        10, Int.MAX_VALUE,
        60L, TimeUnit.SECONDS,
        SynchronousQueue()
    )

    fun credit() {
        logger.info("Requesting credit service")
        try {
            testClient.test(arrayOf("200", "404", "500").random())
        } catch (e: Exception) {

        }
        try {
            testClient.testb()
        } catch (e: Exception) {

        }
        logger.info("Credit service requested")
    }

}
