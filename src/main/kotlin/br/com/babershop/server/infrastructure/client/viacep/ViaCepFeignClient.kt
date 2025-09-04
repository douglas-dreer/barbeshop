package br.com.babershop.server.infrastructure.client.viacep

import br.com.babershop.server.infrastructure.client.viacep.config.ViaCepFeignClientConfig
import br.com.babershop.server.infrastructure.client.viacep.dto.ViaCepResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "viacep-client", url = "https://viacep.com.br/ws",
    configuration = [ViaCepFeignClientConfig::class] )
interface ViaCepFeignClient {
    @GetMapping("/{cep}/{formato}")
    fun buscarCep(@PathVariable cep: String, @PathVariable formato: String): ViaCepResponse
}