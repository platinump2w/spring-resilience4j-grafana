package br.com.gfelipe.springbootresilience4j.config

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.core.region.RegionProvider
import org.springframework.cloud.aws.core.region.StaticRegionProvider
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableSqs
@Configuration
class SqsConfig(
    @Value(value = "\${aws.client.endpoint}")
    private val awsClientEndpoint: String
) {

    @Bean
    fun amazonSqs(): AmazonSQSAsync {
        return AmazonSQSAsyncClientBuilder.standard()
            .withEndpointConfiguration(EndpointConfiguration(awsClientEndpoint, Regions.US_EAST_1.getName()))
            .withCredentials(awsCredentialsProvider())
            .build()
    }

    @Bean
    fun awsCredentialsProvider(): AWSCredentialsProvider {
        val basicAWSCredentials = BasicAWSCredentials("test", "test")
        return AWSStaticCredentialsProvider(basicAWSCredentials)
    }

    @Bean
    fun awsRegionProvider(): RegionProvider = StaticRegionProvider(Regions.US_EAST_1.getName())

    @Bean
    fun queueMessagingTemplate(amazonSqs: AmazonSQSAsync): QueueMessagingTemplate =
        QueueMessagingTemplate(amazonSqs)

}
