package br.com.babershop.server.infrastructure.client.viacep.config

import br.com.babershop.server.infrastructure.client.viacep.exception.ViaCepErrorDecoder
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ViaCepFeignClientConfig {
    @Bean
    fun viaCepErrorDecoder(): ErrorDecoder {
        return ViaCepErrorDecoder()
    }
}