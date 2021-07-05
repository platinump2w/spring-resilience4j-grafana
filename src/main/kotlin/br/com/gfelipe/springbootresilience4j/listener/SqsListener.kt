package br.com.gfelipe.springbootresilience4j.listener

import br.com.gfelipe.springbootresilience4j.service.TestService
import org.slf4j.LoggerFactory
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class TestSqsListener(
    private val testService: TestService
) {

    val logger = LoggerFactory.getLogger(TestSqsListener::class.java)

    @SqsListener(value = ["\${aws.sqs.test.queue}"])
    fun exampleQueueListener(message: String) {
        logger.info("Consumed message: $message")
        testService.credit()
        logger.info("Finished message consumption")
    }

}
