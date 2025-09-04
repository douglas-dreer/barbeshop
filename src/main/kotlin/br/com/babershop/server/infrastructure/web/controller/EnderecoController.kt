package br.com.babershop.server.infrastructure.web.controller

import br.com.babershop.server.domain.port.input.cep.BuscarEnderecoPorCepUseCase
import br.com.babershop.server.infrastructure.annotation.Logavel
import br.com.babershop.server.infrastructure.web.mapper.toResponse
import br.com.babershop.server.infrastructure.web.response.EnderecoResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/enderecos")
class EnderecoController(
    private final val buscarEnderecoPorCepUseCase: BuscarEnderecoPorCepUseCase
) {
    @Logavel
    @GetMapping(params = ["cep", "formato"])
    fun buscarEnderecoPorCep(
        @RequestParam("cep") cep: String,
        @RequestParam("formato", required = true, defaultValue = "json") formato: String
    ): ResponseEntity<EnderecoResponse>{
        val enderecoResultado = buscarEnderecoPorCepUseCase.execute(cep, formato)
        return ResponseEntity.ok(enderecoResultado.toResponse())
    }
}